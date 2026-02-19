# Contributing to Labor Marketplace

Thank you for your interest in contributing to the Labor Marketplace project! 

## Code of Conduct

- Be respectful and inclusive
- Focus on constructive feedback
- Help others learn and grow

---

## Getting Started

### 1. Fork & Clone
```bash
git clone https://github.com/your-username/labor-marketplace.git
cd labor
```

### 2. Setup Development Environment
```bash
# Install dependencies
mvn clean install

# Setup database
./setup-database.sh

# Start application
mvn spring-boot:run
```

### 3. Create Feature Branch
```bash
git checkout -b feature/your-feature-name
```

---

## Development Guidelines

### Code Style
- Use meaningful variable names
- Follow Java naming conventions (camelCase)
- Add comments for complex logic
- Keep methods focused and small

### Git Commits
```bash
# Format: type: description
# Example: feature: add email notifications
# Example: fix: prevent duplicate job posts
# Example: docs: update database guide

git commit -m "feature: add email notifications"
```

### Commit Types
- `feature:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation
- `style:` - Code style (no logic change)
- `refactor:` - Code restructuring
- `test:` - Test additions
- `chore:` - Maintenance

---

## Testing

### Before Submitting PR
1. Run tests: `mvn test`
2. Check for errors: `mvn clean compile`
3. Verify database: 
   ```bash
   psql -U labor_user -d labor_marketplace
   \dt  # List tables
   ```

### Manual Testing
- Test in both English and Marathi
- Test on mobile and desktop
- Verify database operations
- Check error handling

---

## Database Changes

If you modify the database schema:

1. Update `schema.sql`
2. Update `init-database.sql`
3. Run `./clean-slate-database.sh` to test
4. Document changes in PR

---

## Frontend Changes

### HTML/CSS
- Maintain responsive design
- Update both English and Marathi content
- Add `data-i18n` attributes for translations
- Test in multiple browsers

### JavaScript
- Use modern ES6+ syntax
- Use `dataset` API instead of `getAttribute`
- Use optional chaining `?.`
- Add comments for complex logic

### Translations
Update `src/main/resources/templates/translations.js`:
```javascript
const translations = {
  en: {
    myKey: "English text"
  },
  mr: {
    myKey: "मराठी मजकूर"
  }
};
```

---

## Backend Changes

### Java Code
- Follow Spring Boot conventions
- Use JPA repositories for database access
- Add Javadoc for public methods
- Use constants for repeated values

### Controllers
```java
@PostMapping("/your-endpoint")
public String yourMethod(Model model) {
    // Implementation
    return "view-name";
}
```

### Repositories
```java
public interface YourRepository extends JpaRepository<Entity, Long> {
    List<Entity> findByYourField(String value);
}
```

---

## Pull Request Process

### Before Submitting
1. Pull latest from main: `git pull origin main`
2. Rebase your branch: `git rebase origin/main`
3. Test thoroughly
4. Update documentation

### Create PR
1. Go to GitHub repository
2. Click "New Pull Request"
3. Select your branch
4. Add title and description
5. Reference related issues (#123)
6. Submit!

### PR Description Template
```markdown
## Description
Brief description of changes

## Type of Change
- [ ] New feature
- [ ] Bug fix
- [ ] Documentation

## Testing
How to test these changes

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added
- [ ] Documentation updated
- [ ] No new errors introduced
- [ ] Tested in English and Marathi
```

---

## Common Development Tasks

### Adding a New Feature

1. **Controller**
```java
@GetMapping("/your-route")
public String yourRoute(Model model) {
    model.addAttribute("data", yourData);
    return "your-template";
}
```

2. **Template (HTML)**
```html
<h1 data-i18n="your_key">English Title</h1>
```

3. **Translations**
```javascript
const translations = {
  en: { your_key: "English Title" },
  mr: { your_key: "मराठी शीर्षक" }
};
```

4. **Test thoroughly**

### Adding Database Changes

1. **Update schema**
```sql
ALTER TABLE table_name ADD COLUMN new_column VARCHAR(100);
```

2. **Update Entity**
```java
@Column(name = "new_column")
private String newColumn;
```

3. **Update Repository if needed**

4. **Run reset**
```bash
./clean-slate-database.sh
mvn spring-boot:run
```

---

## Common Issues

### "Port 8080 already in use"
```bash
lsof -ti:8080 | xargs kill -9
```

### "Database connection failed"
```bash
./setup-database.sh
```

### "Build fails"
```bash
mvn clean install -U
```

### "Thymeleaf template not found"
- Check filename spelling
- Verify file is in `src/main/resources/templates/`
- Check return statement in controller

---

## Code Review Checklist

Reviewers will check:
- ✅ Code style consistency
- ✅ No hardcoded values
- ✅ Proper error handling
- ✅ Database query optimization
- ✅ i18n support (English/Marathi)
- ✅ Mobile responsive
- ✅ Security (no SQL injection, XSS)
- ✅ Performance
- ✅ Tests passing
- ✅ Documentation updated

---

## Reporting Bugs

Create an issue with:
1. **Title** - Clear bug description
2. **Environment** - OS, Java version, etc.
3. **Steps to Reproduce** - Exact steps to trigger bug
4. **Expected Behavior** - What should happen
5. **Actual Behavior** - What actually happens
6. **Screenshots** - If applicable
7. **Logs** - Error logs or terminal output

---

## Suggesting Features

Create an issue with:
1. **Title** - Feature description
2. **Motivation** - Why this is needed
3. **Use Case** - How it helps users
4. **Solution** - Proposed implementation
5. **Alternatives** - Other approaches considered

---

## Documentation

### Updating Docs
- Update README.md for user-facing changes
- Update DATABASE-SETUP.md for database changes
- Add Javadoc for new public methods
- Update this CONTRIBUTING.md if needed

### Good Documentation
- Clear and concise
- Code examples where helpful
- Links to related docs
- Both English explanations

---

## Need Help?

- Ask questions in PRs
- Check existing issues
- Review code comments
- Check documentation

---

## Recognition

Contributors will be recognized in:
- Git commit history
- Project documentation
- GitHub contributors page

Thank you for contributing! 🎉
