# 🌾 Labor Marketplace

A bilingual (English/Marathi) web application connecting farmers and workers in Maharashtra, India. Built with Spring Boot, PostgreSQL, and modern web technologies.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Usage](#usage)
- [Architecture](#architecture)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

---

## ✨ Features

### Core Features
- 🔐 **User Authentication & Authorization**
  - Secure login/registration
  - Role-based access (Farmer/Worker)
  - Password encryption with Spring Security

- 💼 **Job Management**
  - Post and manage job listings
  - Set wage, location, skills required
  - Track worker responses
  - Mark jobs as full

- 👥 **Worker Profiles**
  - Post availability and skills
  - Set preferred work locations
  - View job opportunities

- 🔍 **Smart Filtering & Search**
  - Filter by town/location
  - Filter by gender preference
  - Sort by wage and date
  - Real-time search functionality

### Localization
- 🌐 **Bilingual Support**
  - English and Marathi translations
  - 60+ town names in Marathi
  - Instant language switching
  - Persistent language preference (localStorage)

### UI/UX
- 🎨 **Modern Design**
  - Custom theme with green/orange/teal color scheme
  - Responsive layout (mobile, tablet, desktop)
  - Smooth animations and transitions
  - Accessibility compliant

---

## 🛠️ Tech Stack

### Backend
- **Java 17+** - Programming language
- **Spring Boot 3.2.0** - Web framework
- **Spring Data JPA** - Database ORM
- **Spring Security** - Authentication & Authorization
- **PostgreSQL 12+** - Database
- **Maven** - Build tool

### Frontend
- **HTML5** - Markup
- **CSS3** - Styling with custom theme
- **JavaScript (Vanilla)** - Interactive features
- **Bootstrap 5.3.2** - Responsive framework
- **Bootstrap Icons** - Icon library
- **Thymeleaf** - Template engine

### Development Tools
- **VS Code** - Editor
- **Git** - Version control
- **Docker** (optional) - Containerization

---

## 📁 Project Structure

```
labor/
├── src/
│   ├── main/
│   │   ├── java/com/labor/
│   │   │   ├── LaborApplication.java          # Main app entry
│   │   │   ├── DataLoader.java                # Initial data loader
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java        # Spring Security setup
│   │   │   ├── controller/
│   │   │   │   ├── HomeController.java        # Home/landing routes
│   │   │   │   ├── UserController.java        # Auth & user routes
│   │   │   │   └── JobController.java         # Job management routes
│   │   │   ├── model/
│   │   │   │   ├── User.java                  # User entity
│   │   │   │   ├── JobPost.java               # Job entity
│   │   │   │   ├── Response.java              # Job response entity
│   │   │   │   ├── WorkerAvailability.java    # Worker profile entity
│   │   │   │   ├── Town.java                  # Town entity
│   │   │   │   └── Response.java              # Generic response DTO
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java        # User data access
│   │   │   │   ├── JobPostRepository.java     # Job data access
│   │   │   │   ├── ResponseRepository.java    # Response data access
│   │   │   │   ├── WorkerAvailabilityRepository.java
│   │   │   │   └── TownRepository.java        # Town data access
│   │   │   └── service/
│   │   │       └── CustomUserDetailsService.java # User authentication
│   │   └── resources/
│   │       ├── application.properties         # App configuration
│   │       └── templates/
│   │           ├── landing.html               # Home page
│   │           ├── login.html                 # Login page
│   │           ├── register.html              # Registration page
│   │           ├── worker_feed.html           # Worker dashboard
│   │           ├── farmer_dashboard.html      # Farmer dashboard
│   │           ├── post_job.html              # Post job form
│   │           ├── post_availability.html     # Post availability form
│   │           ├── custom-theme.css           # Custom styling
│   │           └── translations.js            # i18n translations
│   └── target/                                # Compiled output
├── pom.xml                                    # Maven dependencies
├── schema.sql                                 # Database schema
├── setup-database.sh                          # Database setup script
├── clean-slate-database.sh                    # Database reset script
├── init-database.sql                          # SQL initialization
├── DATABASE-SETUP.md                          # Database guide
├── DB-QUICK-REFERENCE.md                      # Database quick ref
└── README.md                                  # This file
```

---

## 📦 Prerequisites

### System Requirements
- **Java 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **PostgreSQL 12+** - [Download](https://www.postgresql.org/download/)
- **Git** - [Download](https://git-scm.com/download)

### Verify Installation
```bash
java -version
mvn -version
psql --version
git --version
```

---

## 🚀 Quick Start

### 1. Clone Repository
```bash
cd /Users/pankaj-mac/test/myprojects
git clone <repository-url> labor
cd labor
```

### 2. Setup Database
```bash
# Make scripts executable
chmod +x setup-database.sh clean-slate-database.sh

# Run setup (creates fresh database)
./setup-database.sh

# Or reset completely (delete all data)
./clean-slate-database.sh
```

**Database Credentials:**
- Database: `labor_marketplace`
- User: `labor_user`
- Password: `labor_password_123`

### 3. Build Application
```bash
mvn clean compile
```

### 4. Start Application
```bash
mvn spring-boot:run
```

### 5. Access Application
Open browser and navigate to: **http://localhost:8080**

---

## 🗄️ Database Setup

### Quick Setup Commands

```bash
# Initial setup
./setup-database.sh

# Reset to clean state
./clean-slate-database.sh

# Connect to database
psql -U labor_user -d labor_marketplace

# Backup database
pg_dump -U labor_user labor_marketplace > backup.sql

# Restore database
./clean-slate-database.sh
psql -U labor_user -d labor_marketplace < backup.sql
```

### Database Schema

**5 Main Tables:**

1. **town** - Marathi towns (60+ records)
2. **users** - Farmer & Worker profiles
3. **job_post** - Job listings by farmers
4. **response** - Worker applications to jobs
5. **worker_availability** - Worker availability profiles

**Key Relationships:**
```
Town → Users → JobPost → Response
       ↓
       WorkerAvailability
```

For detailed database documentation, see: [`DATABASE-SETUP.md`](DATABASE-SETUP.md)

---

## ⚙️ Configuration

### Application Properties
File: `src/main/resources/application.properties`

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/labor_marketplace
spring.datasource.username=labor_user
spring.datasource.password=labor_password_123

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Environment Variables
```bash
export DB_PASSWORD='labor_password_123'
export SPRING_DATASOURCE_PASSWORD='labor_password_123'
```

---

## 📖 Usage

### For Farmers
1. **Register** - Create account with role "Farmer"
2. **Post Jobs** - Click "Post Job" to create job listings
3. **Manage Jobs** - View responses and mark jobs as full
4. **Browse Workers** - See available workers in your area

### For Workers
1. **Register** - Create account with role "Worker"
2. **Post Availability** - Share skills and availability
3. **Browse Jobs** - Search jobs by location and wage
4. **Apply** - Express interest in jobs
5. **Track Status** - View job responses

### Language
- Click **English/मराठी** dropdown in navbar
- Language switches instantly
- Preference saved automatically

---

## 🏗️ Architecture

### MVC Pattern
```
Request → Router (Controller) → Service → Repository → Database
           ↓
        Response
        (Thymeleaf Template)
```

### Security Flow
```
Login Form → UserController → CustomUserDetailsService
              ↓
         Spring Security → Session Created
              ↓
         Redirect to Dashboard
```

### Data Flow
1. **Frontend** - HTML forms capture user input
2. **Controller** - Routes requests, validates input
3. **Service Layer** - Business logic (future enhancement)
4. **Repository** - Database access via Spring Data JPA
5. **Database** - PostgreSQL persistence

---

## 🔌 API Endpoints

### Public Endpoints
- `GET /` - Landing page
- `GET /login` - Login page
- `GET /register` - Registration page
- `POST /register` - Register new user

### Authenticated Endpoints

#### User Routes
- `GET /dashboard` - Redirects to role-based dashboard
- `POST /logout` - User logout

#### Farmer Routes
- `GET /farmer-dashboard` - Farmer dashboard
- `POST /post-job` - Create job post
- `POST /mark-full/{jobId}` - Mark job as full
- `POST /delete-job/{jobId}` - Delete job post

#### Worker Routes
- `GET /worker-feed` - Worker job feed
- `POST /post-availability` - Create availability profile
- `POST /express-interest/{jobId}` - Apply for job
- `POST /delete-availability/{id}` - Delete availability

---

## 🔐 Security Features

- ✅ Password encryption (BCrypt)
- ✅ Session-based authentication
- ✅ CSRF protection
- ✅ Role-based access control (Farmer/Worker)
- ✅ Mobile number uniqueness constraint
- ✅ SQL injection prevention (parameterized queries)

---

## 🌐 Internationalization (i18n)

### Supported Languages
- English (en)
- Marathi (mr)

### Translation Files
- `src/main/resources/templates/translations.js` - Translation object
- Town names: 60+ Marathi translations

### Adding New Translations
Edit `translations.js`:
```javascript
const translations = {
  en: { key: "English text" },
  mr: { key: "मराठी मजकूर" }
};
```

---

## 📊 Sample Data

### Test Credentials
After setup, database includes 60+ towns. You can:
1. Register as Farmer with mobile: 9876543210
2. Register as Worker with mobile: 9876543211
3. Create test job posts and applications

---

## 🐛 Troubleshooting

| Problem | Solution |
|---------|----------|
| Port 8080 in use | `lsof -ti:8080 \| xargs kill -9` |
| Database connection error | Verify PostgreSQL is running |
| "Database already exists" | Run `./clean-slate-database.sh` |
| Build fails | Run `mvn clean install` |
| Blank page loading | Clear browser cache and reload |

---

## 📈 Future Enhancements

- [ ] Email notifications
- [ ] SMS notifications
- [ ] Rating & review system
- [ ] Payment integration
- [ ] Advanced search filters
- [ ] Map integration
- [ ] Analytics dashboard
- [ ] Mobile app (React Native)
- [ ] Docker containerization
- [ ] CI/CD pipeline

---

## 📝 Code Quality

- ✅ Zero VS Code errors
- ✅ SonarQube compliant
- ✅ Accessibility WCAG compliant
- ✅ Optional chaining in JavaScript
- ✅ Proper indexing in database

---

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

---

## 📄 License

This project is proprietary and for educational purposes.

---

## 👨‍💻 Author

**Indrajeet** - Created for farmers and workers in Maharashtra

---

## 📞 Support

For issues or questions:
1. Check [`DATABASE-SETUP.md`](DATABASE-SETUP.md) for database issues
2. Review [`DB-QUICK-REFERENCE.md`](DB-QUICK-REFERENCE.md) for quick commands
3. Check application logs for error details

---

## 🎯 Getting Started Checklist

- [ ] Install Java 17+
- [ ] Install PostgreSQL
- [ ] Clone repository
- [ ] Run `./setup-database.sh`
- [ ] Run `mvn clean compile`
- [ ] Run `mvn spring-boot:run`
- [ ] Open http://localhost:8080
- [ ] Register as Farmer or Worker
- [ ] Test language switching
- [ ] Create test job post
- [ ] Apply for job

---

## 📚 Documentation

- [Database Setup Guide](DATABASE-SETUP.md)
- [Database Quick Reference](DB-QUICK-REFERENCE.md)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [PostgreSQL Docs](https://www.postgresql.org/docs/)

---

**Happy coding! 🚀**

Last Updated: February 19, 2026
