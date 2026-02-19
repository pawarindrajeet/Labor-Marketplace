# 📖 Project Documentation Index

Welcome to the Labor Marketplace documentation! Here's what's available:

---

## 🚀 Getting Started

### Start Here
- **[README.md](README.md)** - Main project documentation
  - Project overview and features
  - Quick start guide (5 minutes to running)
  - Tech stack and architecture
  - Troubleshooting

---

## 🗄️ Database

### Database Setup
- **[DATABASE-SETUP.md](DATABASE-SETUP.md)** - Complete database guide (7.5KB)
  - 4 installation methods
  - Schema documentation
  - Sample queries
  - Backup & restore
  - Troubleshooting

### Quick Reference
- **[DB-QUICK-REFERENCE.md](DB-QUICK-REFERENCE.md)** - Quick commands (2.1KB)
  - Common database commands
  - Connection strings
  - Quick troubleshooting

### Database Scripts
- `setup-database.sh` - Initial setup (creates fresh DB)
- `clean-slate-database.sh` - Complete reset (deletes all data)
- `init-database.sql` - SQL schema only

---

## 👨‍💻 Development

### Contributing
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Developer guide (6.2KB)
  - Development setup
  - Code style guidelines
  - Git workflow
  - PR process
  - Testing procedures

---

## 📁 File Organization

```
labor/
├── 📖 README.md                    (Main documentation)
├── 📘 CONTRIBUTING.md              (Developer guide)
├── 📕 DATABASE-SETUP.md            (Database guide)
├── 📙 DB-QUICK-REFERENCE.md        (Quick commands)
│
├── 🗄️ src/                         (Source code)
│   └── main/
│       ├── java/com/labor/         (Backend)
│       └── resources/              (Frontend + config)
│
├── 🔧 Database Scripts
│   ├── setup-database.sh           (New DB)
│   ├── clean-slate-database.sh     (Reset DB)
│   ├── init-database.sql           (SQL schema)
│   └── schema.sql                  (Old schema ref)
│
└── 🔨 Build Files
    └── pom.xml                     (Maven config)
```

---

## 🎯 Quick Navigation

### I want to...

#### Get the app running
→ Read [README.md](README.md) - "Quick Start" section (5 min)

#### Set up the database
→ Read [DB-QUICK-REFERENCE.md](DB-QUICK-REFERENCE.md) (1 min)
→ Or [DATABASE-SETUP.md](DATABASE-SETUP.md) (detailed)

#### Add a new feature
→ Read [CONTRIBUTING.md](CONTRIBUTING.md) - "Adding a New Feature"

#### Report a bug
→ Read [CONTRIBUTING.md](CONTRIBUTING.md) - "Reporting Bugs"

#### Reset the database
→ Run `./clean-slate-database.sh`

#### Connect to database
→ Run `psql -U labor_user -d labor_marketplace`

#### Start the app
→ Run `mvn spring-boot:run`

#### Change language
→ Click dropdown in browser navbar

---

## 📊 Documentation Stats

| File | Size | Lines | Content |
|------|------|-------|---------|
| README.md | 13KB | 490 | Project overview, quick start, architecture |
| CONTRIBUTING.md | 6.2KB | 310 | Developer guide, code style, PR process |
| DATABASE-SETUP.md | 7.5KB | 380 | Database guide, schema, troubleshooting |
| DB-QUICK-REFERENCE.md | 2.1KB | 70 | Quick commands reference |
| **Total** | **28.8KB** | **1,250** | Complete project documentation |

---

## ✨ Features Documented

### User Features
- ✅ User registration & login
- ✅ Farmer job posting
- ✅ Worker availability posting
- ✅ Job browsing & filtering
- ✅ Apply for jobs
- ✅ Language switching (English/Marathi)

### Technical Features
- ✅ Spring Boot backend
- ✅ PostgreSQL database
- ✅ JWT/Session authentication
- ✅ Responsive design
- ✅ i18n support
- ✅ Role-based access control

### Dev Features
- ✅ Database setup scripts
- ✅ Maven build system
- ✅ Development guidelines
- ✅ Troubleshooting guides
- ✅ Code style standards

---

## 🚀 5-Minute Quickstart

1. **Database**
   ```bash
   ./setup-database.sh
   ```

2. **Build**
   ```bash
   mvn clean compile
   ```

3. **Run**
   ```bash
   mvn spring-boot:run
   ```

4. **Access**
   - Open: http://localhost:8080
   - Register as Farmer or Worker
   - Test language switching

---

## 🔗 Related Resources

### External Documentation
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [Bootstrap Docs](https://getbootstrap.com/docs)
- [Thymeleaf Docs](https://www.thymeleaf.org/documentation.html)

### Java Resources
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)
- [Spring Security Guide](https://spring.io/guides/gs/securing-web/)
- [JPA & Hibernate](https://hibernate.org/orm/documentation/)

---

## 💡 Pro Tips

- 📌 Pin this index page for quick reference
- 🔍 Use Ctrl+F to search within documentation
- 📱 Mobile-friendly documentation available
- 🌐 All docs support both English and technical terms
- 💾 Keep database backups regularly
- 🔄 Use `git pull` before starting new features

---

## 🆘 Getting Help

1. **Check the docs** - Most questions answered here
2. **Search issues** - Check existing GitHub issues
3. **Review code** - Comments explain complex logic
4. **Ask in PRs** - Community is helpful

---

## 📝 Contributing to Docs

Want to improve documentation?

1. Fork the repo
2. Edit `.md` files
3. Create pull request
4. Describe your changes

See [CONTRIBUTING.md](CONTRIBUTING.md) for details.

---

## ✅ Checklist: Before Starting Development

- [ ] Read [README.md](README.md)
- [ ] Set up database with `./setup-database.sh`
- [ ] Run `mvn clean compile`
- [ ] Start app with `mvn spring-boot:run`
- [ ] Test at http://localhost:8080
- [ ] Read [CONTRIBUTING.md](CONTRIBUTING.md)
- [ ] Create feature branch
- [ ] Make your changes
- [ ] Test thoroughly
- [ ] Submit PR

---

## 📅 Last Updated

- **Date**: February 2, 2026
- **Version**: 1.0.0
- **Status**: Production Ready

---

## 🎉 Thank You!

Thank you for using Labor Marketplace. We hope this documentation helps you get started quickly!

Happy coding! 🚀

---

**Questions?** Check the relevant documentation file above!
