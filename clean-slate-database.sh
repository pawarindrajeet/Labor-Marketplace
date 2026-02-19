#!/bin/bash
# Labor Marketplace Database Clean Slate Script
# This script completely removes and recreates the database for a fresh start

set -e  # Exit on error

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
DB_NAME="labor_marketplace"
DB_USER="labor_user"
DB_PASSWORD="labor_password_123"
DB_HOST="localhost"
DB_PORT="5432"
PSQL_USER="postgres"

echo -e "${BLUE}========================================${NC}"
echo -e "${RED}⚠️  Labor Marketplace Database Clean Slate${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""
echo -e "${YELLOW}WARNING: This will DELETE all data!${NC}"
echo "Database: $DB_NAME"
echo ""
read -p "Are you sure you want to proceed? (type 'yes' to confirm): " CONFIRM

if [ "$CONFIRM" != "yes" ]; then
    echo -e "${YELLOW}Cancelled. No changes made.${NC}"
    exit 0
fi

echo ""
echo -e "${YELLOW}Checking PostgreSQL connection...${NC}"

# Check if PostgreSQL is installed
if ! command -v psql &> /dev/null; then
    echo -e "${RED}Error: PostgreSQL is not installed or psql is not in PATH${NC}"
    exit 1
fi

# Check if PostgreSQL service is running
if ! pg_isready -h "$DB_HOST" -p "$DB_PORT" &> /dev/null; then
    echo -e "${RED}Error: PostgreSQL is not running on $DB_HOST:$DB_PORT${NC}"
    exit 1
fi

echo -e "${GREEN}✓ PostgreSQL is running${NC}"
echo ""

# Terminate all connections to the database
echo -e "${YELLOW}Terminating database connections...${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" << EOF
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = '$DB_NAME'
  AND pid <> pg_backend_pid();
EOF

echo -e "${GREEN}✓ Connections terminated${NC}"
echo ""

# Drop database
echo -e "${YELLOW}Dropping database: $DB_NAME${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -c "DROP DATABASE IF EXISTS $DB_NAME;" 2>/dev/null || true

echo -e "${GREEN}✓ Database dropped${NC}"
echo ""

# Drop user
echo -e "${YELLOW}Dropping database user: $DB_USER${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -c "DROP USER IF EXISTS $DB_USER;" 2>/dev/null || true

echo -e "${GREEN}✓ User dropped${NC}"
echo ""

# Create database
echo -e "${YELLOW}Creating fresh database: $DB_NAME${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -c "CREATE DATABASE $DB_NAME;"

echo -e "${GREEN}✓ Database created${NC}"
echo ""

# Create user
echo -e "${YELLOW}Creating database user: $DB_USER${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -c "CREATE USER $DB_USER WITH PASSWORD '$DB_PASSWORD';"

echo -e "${GREEN}✓ User created${NC}"
echo ""

# Grant privileges
echo -e "${YELLOW}Granting privileges to $DB_USER...${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -d "$DB_NAME" << EOF
GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO $DB_USER;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO $DB_USER;
EOF

echo -e "${GREEN}✓ Privileges granted${NC}"
echo ""

# Create tables
echo -e "${YELLOW}Creating database tables...${NC}"

PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$DB_USER" -d "$DB_NAME" << 'EOF'
-- Towns table
CREATE TABLE IF NOT EXISTS town (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('Farmer', 'Worker')) NOT NULL,
    gender VARCHAR(10),
    town_id INTEGER REFERENCES town(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Job Posts table
CREATE TABLE IF NOT EXISTS job_post (
    id SERIAL PRIMARY KEY,
    farmer_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    town_id INTEGER REFERENCES town(id),
    work_type VARCHAR(200) NOT NULL,
    description TEXT,
    wage NUMERIC(10,2),
    workers_needed INTEGER NOT NULL,
    workers_responded INTEGER DEFAULT 0,
    is_full BOOLEAN DEFAULT FALSE,
    skills_required TEXT,
    preferred_gender VARCHAR(10),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Responses table
CREATE TABLE IF NOT EXISTS response (
    id SERIAL PRIMARY KEY,
    job_id INTEGER REFERENCES job_post(id) ON DELETE CASCADE,
    worker_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    responded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (job_id, worker_id)
);

-- Worker Availability table
CREATE TABLE IF NOT EXISTS worker_availability (
    id SERIAL PRIMARY KEY,
    worker_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    skills TEXT,
    availability TEXT,
    wage NUMERIC(10,2),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_users_town ON users(town_id);
CREATE INDEX IF NOT EXISTS idx_users_mobile ON users(mobile);
CREATE INDEX IF NOT EXISTS idx_job_post_farmer ON job_post(farmer_id);
CREATE INDEX IF NOT EXISTS idx_job_post_town ON job_post(town_id);
CREATE INDEX IF NOT EXISTS idx_job_post_is_full ON job_post(is_full);
CREATE INDEX IF NOT EXISTS idx_job_post_created ON job_post(created_at DESC);
CREATE INDEX IF NOT EXISTS idx_response_job ON response(job_id);
CREATE INDEX IF NOT EXISTS idx_response_worker ON response(worker_id);
CREATE INDEX IF NOT EXISTS idx_worker_availability_worker ON worker_availability(worker_id);
CREATE INDEX IF NOT EXISTS idx_worker_availability_created ON worker_availability(created_at DESC);

EOF

echo -e "${GREEN}✓ Tables created${NC}"
echo ""

# Insert towns data
echo -e "${YELLOW}Populating towns data...${NC}"

PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$DB_USER" -d "$DB_NAME" << 'EOF'
INSERT INTO town (name) VALUES
('Agar'),
('Ankali'),
('Arag'),
('Balwad'),
('Bamnoli'),
('Bedag'),
('Belanki'),
('Bhose'),
('Bijageri'),
('Brahmanpuri'),
('Budhavgaon'),
('Chinchani'),
('Dhavali'),
('Dudhani'),
('Ganeshpur'),
('Garlawad'),
('Gudhe'),
('Gulvanchi'),
('Harnal'),
('Ingalgi'),
('Jambli'),
('Kadiyanal'),
('Kavthepiran'),
('Khanderajuri'),
('Kukudwad'),
('Kupwad'),
('Laxmiwadi'),
('Madihal'),
('Malgaon'),
('Maliwadi'),
('Mangrul'),
('Miraj'),
('Mhaisal'),
('Narwad'),
('Nandre'),
('Nandani'),
('Nimshirgaon'),
('Padali'),
('Patgaon'),
('Rupbhavani'),
('Sagara'),
('Sangli'),
('Sangliwadi'),
('Soni'),
('Tupari'),
('Wanlesswadi'),
('Yesugade'),
('Alate'),
('Amaljheri'),
('Anjani'),
('Balawadi'),
('Bhilawadi'),
('Borgaon'),
('Choudeshwar'),
('Dahiwadi'),
('Dhamani'),
('Dhawali'),
('Dhavalipur'),
('Dongarsoni'),
('Ghanand'),
('Ghatnandre')
ON CONFLICT (name) DO NOTHING;

EOF

echo -e "${GREEN}✓ Towns data populated${NC}"
echo ""

# Display summary
echo -e "${BLUE}========================================${NC}"
echo -e "${GREEN}✓ Database Reset Complete!${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""
echo -e "${YELLOW}Database Information:${NC}"
echo -e "  Database: ${GREEN}$DB_NAME${NC}"
echo -e "  User: ${GREEN}$DB_USER${NC}"
echo -e "  Host: ${GREEN}$DB_HOST:$DB_PORT${NC}"
echo ""
echo -e "${YELLOW}Status:${NC}"
echo -e "  ✓ All previous data deleted"
echo -e "  ✓ Fresh tables created"
echo -e "  ✓ Indexes created"
echo -e "  ✓ 60+ towns populated"
echo ""
echo -e "${YELLOW}Ready to start fresh!${NC}"
echo -e "${BLUE}========================================${NC}"
