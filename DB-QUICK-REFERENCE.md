# Database Setup - Quick Reference

## 📋 Files Overview

| File | Purpose | When to Use |
|------|---------|-----------|
| `setup-database.sh` | Initial database setup | First-time setup or new database |
| `clean-slate-database.sh` | Complete database reset | Start fresh, delete all data |
| `init-database.sql` | SQL schema only | Manual setup or without scripts |
| `DATABASE-SETUP.md` | Complete guide | For detailed instructions |

---

## 🚀 Quick Start (3 Steps)

### Step 1: Ensure PostgreSQL is Running

**macOS:**
```bash
brew services start postgresql@15
```

**Linux:**
```bash
sudo systemctl start postgresql
```

### Step 2: Run Setup Script

```bash
cd /Users/pankaj-mac/test/myprojects/labor
./setup-database.sh
```

### Step 3: Start the Application

```bash
mvn spring-boot:run
```

**✅ Done!** Visit http://localhost:8080

---

## 🔄 Database Credentials

- **Database:** labor_marketplace
- **User:** labor_user  
- **Password:** labor_password_123
- **Host:** localhost
- **Port:** 5432

---

## 💾 Available Commands

### Setup New Database
```bash
./setup-database.sh
```

### Reset Database (Delete All Data)
```bash
./clean-slate-database.sh
```

### Connect to Database
```bash
psql -U labor_user -d labor_marketplace
```

### Backup Database
```bash
pg_dump -U labor_user labor_marketplace > backup.sql
```

### Restore from Backup
```bash
./clean-slate-database.sh
psql -U labor_user -d labor_marketplace < backup.sql
```

---

## 📊 Database Contents

After setup:
- **60+ towns** (Marathi cities)
- **5 tables** (town, users, job_post, response, worker_availability)
- **Performance indexes** created
- **Cascading deletes** enabled for data integrity

---

## 🛠️ Troubleshooting

| Issue | Solution |
|-------|----------|
| "PostgreSQL not running" | `brew services start postgresql@15` |
| "Database already exists" | Run `./clean-slate-database.sh` |
| Connection refused | Verify PostgreSQL is running on localhost:5432 |
| Permission denied | Run as postgres user: `sudo -u postgres` |

---

## 📖 Full Guide

For complete documentation, see: `DATABASE-SETUP.md`

---

**Ready to build! 🎉**
