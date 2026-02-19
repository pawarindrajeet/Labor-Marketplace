# Labor Marketplace - Database Setup Guide

This guide explains how to set up and manage the PostgreSQL database for the Labor Marketplace application.

## Prerequisites

- PostgreSQL 12+ installed and running
- `psql` command-line tool available in PATH
- Basic understanding of databases and terminal commands

---

## Installation Methods

### Method 1: Using the Setup Script (Recommended - New Database)

The `setup-database.sh` script creates a new database with all required tables and initial data.

```bash
# Make script executable (one-time)
chmod +x setup-database.sh

# Run the setup
./setup-database.sh
```

**What it does:**
- ✅ Creates database `labor_marketplace`
- ✅ Creates user `labor_user` with password `labor_password_123`
- ✅ Creates all required tables (town, users, job_post, response, worker_availability)
- ✅ Creates performance indexes
- ✅ Populates 60+ Marathi town names
- ✅ Sets up proper permissions and constraints

**When to use:**
- Fresh installation
- First-time setup
- New development environment

---

### Method 2: Using the Clean Slate Script (Reset Database)

The `clean-slate-database.sh` script completely wipes the database and creates it fresh.

```bash
# Make script executable (one-time)
chmod +x clean-slate-database.sh

# Run the clean slate reset
./clean-slate-database.sh
```

**What it does:**
- ✅ Terminates all active connections
- ✅ Drops the existing database completely
- ✅ Drops the existing user
- ✅ Creates brand new database and user
- ✅ Creates fresh tables (no data carried over)
- ✅ Populates towns again

**When to use:**
- Starting completely fresh
- Clearing corrupted data
- Testing from a clean state
- Resetting after development/testing

**⚠️ WARNING:** This will DELETE all data! You cannot undo this action.

---

### Method 3: Using SQL Script Directly

Use the `init-database.sql` file directly with psql:

```bash
# Create database and user first (as postgres user)
sudo -u postgres psql << 'EOF'
CREATE DATABASE labor_marketplace;
CREATE USER labor_user WITH PASSWORD 'labor_password_123';
GRANT ALL PRIVILEGES ON DATABASE labor_marketplace TO labor_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO labor_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO labor_user;
EOF

# Then initialize schema
psql -U labor_user -d labor_marketplace -f init-database.sql
```

---

### Method 4: Manual Setup (Advanced)

If you prefer manual setup:

```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE labor_marketplace;

# Create user
CREATE USER labor_user WITH PASSWORD 'labor_password_123';

# Grant privileges
GRANT ALL PRIVILEGES ON DATABASE labor_marketplace TO labor_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO labor_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO labor_user;

# Exit
\q

# Run SQL script
psql -U labor_user -d labor_marketplace -f init-database.sql
```

---

## Configuration

### Environment Variables

Set the database password as an environment variable:

```bash
export DB_PASSWORD='labor_password_123'
```

Or update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/labor_marketplace
spring.datasource.username=labor_user
spring.datasource.password=labor_password_123
```

---

## Database Schema

### Tables

#### `town`
- Stores all available towns/cities
- Contains 60+ Marathi towns
- Used for filtering jobs and workers

#### `users`
- Stores both Farmer and Worker profiles
- Fields: name, mobile (unique), password, role, gender, town_id
- Roles: 'Farmer' or 'Worker'

#### `job_post`
- Job listings created by farmers
- Fields: work_type, wage, workers_needed, skills_required, preferred_gender
- Status: is_full (marks when all positions filled)

#### `response`
- Worker interest/applications for jobs
- Links workers to job posts
- Tracks number of responses per job

#### `worker_availability`
- Worker availability profiles
- Skills, wage expectations, availability dates
- Helps farmers find suitable workers

### Relationships

```
town (1) ──────→ users (M)
           ↑
           │
users (Farmer) (1) ──────→ job_post (M)
                              ↓
                          response (M) ←──── users (Worker)

users (Worker) (1) ──────→ worker_availability (M)
```

---

## Verification

### Check if database exists

```bash
psql -U postgres -l | grep labor_marketplace
```

### Connect to database

```bash
psql -U labor_user -d labor_marketplace
```

### View tables

```sql
\dt
```

### Count records

```sql
SELECT COUNT(*) FROM town;  -- Should show 60
SELECT COUNT(*) FROM users;
SELECT COUNT(*) FROM job_post;
```

### View all towns

```sql
SELECT * FROM town ORDER BY name;
```

---

## Troubleshooting

### PostgreSQL not running

**macOS (Homebrew):**
```bash
brew services start postgresql@15
```

**Ubuntu/Debian:**
```bash
sudo systemctl start postgresql
```

### "database already exists" error

Use the clean slate script:
```bash
./clean-slate-database.sh
```

### Permission denied

Run as PostgreSQL user:
```bash
sudo -u postgres psql
```

### Connection refused

Ensure PostgreSQL service is running and listening on localhost:5432

### "role does not exist" error

The `labor_user` may not have been created. Run setup script again:
```bash
./setup-database.sh
```

---

## Backup & Restore

### Backup database

```bash
pg_dump -U labor_user labor_marketplace > backup.sql
```

### Restore database

```bash
# Create fresh database first
./clean-slate-database.sh

# Restore from backup
psql -U labor_user -d labor_marketplace < backup.sql
```

---

## Sample Queries

### Find active jobs in a town

```sql
SELECT jp.*, u.name as farmer_name, t.name as town_name
FROM job_post jp
JOIN users u ON jp.farmer_id = u.id
JOIN town t ON jp.town_id = t.id
WHERE t.name = 'Sangli' AND jp.is_full = FALSE
ORDER BY jp.created_at DESC;
```

### Find available workers

```sql
SELECT wa.*, u.name as worker_name, u.mobile, t.name as town_name
FROM worker_availability wa
JOIN users u ON wa.worker_id = u.id
JOIN town t ON u.town_id = t.id
WHERE t.name = 'Sangli'
ORDER BY wa.created_at DESC;
```

### Count interests per job

```sql
SELECT j.id, j.work_type, COUNT(r.id) as interests
FROM job_post j
LEFT JOIN response r ON j.id = r.job_id
GROUP BY j.id
ORDER BY interests DESC;
```

---

## Data Integrity

### Cascading Deletes

When a user is deleted, all associated records are automatically deleted:
- Job posts created by farmer
- Worker availability profiles
- Response records (interests)

### Unique Constraints

- User mobile numbers must be unique
- Town names must be unique
- Job + Worker combination in responses is unique (no duplicate applications)

---

## Performance

Indexes are created on frequently queried columns:

- `idx_users_role` - Quick filter by role (Farmer/Worker)
- `idx_job_post_is_full` - Find active jobs
- `idx_job_post_created` - Recent posts ordering
- `idx_response_job` - Find interests for a job
- `idx_worker_availability_created` - Recent availability

---

## Next Steps

1. ✅ Run setup script: `./setup-database.sh`
2. ✅ Verify database: `psql -U labor_user -d labor_marketplace`
3. ✅ Start application: `mvn spring-boot:run`
4. ✅ Access at: http://localhost:8080

---

## Support

For issues or questions:
1. Check troubleshooting section above
2. Verify PostgreSQL is running
3. Ensure database password matches in application.properties
4. Check logs for database connection errors

---

**Happy coding! 🚀**
