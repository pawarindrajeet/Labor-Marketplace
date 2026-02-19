#!/bin/bash
# Labor Marketplace Database Setup Script
# This script creates and initializes the PostgreSQL database for the Labor Marketplace application

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
echo -e "${BLUE}Labor Marketplace Database Setup${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""

# Check if PostgreSQL is installed
if ! command -v psql &> /dev/null; then
    echo -e "${RED}Error: PostgreSQL is not installed or psql is not in PATH${NC}"
    echo "Please install PostgreSQL and try again."
    exit 1
fi

echo -e "${YELLOW}Checking PostgreSQL connection...${NC}"

# Check if PostgreSQL service is running
if ! pg_isready -h "$DB_HOST" -p "$DB_PORT" &> /dev/null; then
    echo -e "${RED}Error: PostgreSQL is not running on $DB_HOST:$DB_PORT${NC}"
    echo "Please start PostgreSQL and try again."
    exit 1
fi

echo -e "${GREEN}✓ PostgreSQL is running${NC}"
echo ""

# Create database
echo -e "${YELLOW}Creating database: $DB_NAME${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -tc "SELECT 1 FROM pg_database WHERE datname = '$DB_NAME'" | grep -q 1 || \
    PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -c "CREATE DATABASE $DB_NAME;"

if PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -d "$DB_NAME" &> /dev/null; then
    echo -e "${GREEN}✓ Database created successfully${NC}"
else
    echo -e "${RED}Error: Failed to create database${NC}"
    exit 1
fi
echo ""

# Create user
echo -e "${YELLOW}Creating database user: $DB_USER${NC}"
PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -U "$PSQL_USER" -tc "SELECT 1 FROM pg_user WHERE usename = '$DB_USER'" | grep -q 1 || \
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
    name VARCHAR(100) NOT NULL UNIQUE
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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Job Posts table
CREATE TABLE IF NOT EXISTS job_post (
    id SERIAL PRIMARY KEY,
    farmer_id INTEGER REFERENCES users(id),
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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Responses table (for worker interest in jobs)
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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_users_town ON users(town_id);
CREATE INDEX IF NOT EXISTS idx_job_post_farmer ON job_post(farmer_id);
CREATE INDEX IF NOT EXISTS idx_job_post_town ON job_post(town_id);
CREATE INDEX IF NOT EXISTS idx_job_post_is_full ON job_post(is_full);
CREATE INDEX IF NOT EXISTS idx_response_job ON response(job_id);
CREATE INDEX IF NOT EXISTS idx_response_worker ON response(worker_id);
CREATE INDEX IF NOT EXISTS idx_worker_availability_worker ON worker_availability(worker_id);

EOF

echo -e "${GREEN}✓ Tables created successfully${NC}"
echo ""

# Insert sample towns data
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
echo -e "${GREEN}Database Setup Complete!${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""
echo -e "${YELLOW}Database Information:${NC}"
echo -e "  Database Name: ${GREEN}$DB_NAME${NC}"
echo -e "  Database User: ${GREEN}$DB_USER${NC}"
echo -e "  Host: ${GREEN}$DB_HOST${NC}"
echo -e "  Port: ${GREEN}$DB_PORT${NC}"
echo ""
echo -e "${YELLOW}Connection String:${NC}"
echo -e "  ${GREEN}postgresql://$DB_USER:$DB_PASSWORD@$DB_HOST:$DB_PORT/$DB_NAME${NC}"
echo ""
echo -e "${YELLOW}Environment Variable (for Spring Boot):${NC}"
echo -e "  ${GREEN}export DB_PASSWORD='$DB_PASSWORD'${NC}"
echo ""
echo -e "${YELLOW}Tables Created:${NC}"
echo -e "  ✓ town (60+ Marathi towns)"
echo -e "  ✓ users (Farmer & Worker profiles)"
echo -e "  ✓ job_post (Job listings)"
echo -e "  ✓ response (Worker applications)"
echo -e "  ✓ worker_availability (Worker profiles)"
echo ""
echo -e "${BLUE}========================================${NC}"
echo -e "${GREEN}Ready to run the application!${NC}"
echo -e "${BLUE}========================================${NC}"
