# Student Microservice (MSV-Students)

This microservice manages student information and their classroom enrollments for Valle Grande.

## 🔧 Project Stack
- **Backend**: Java 17 (Spring Boot, Spring WebFlux - Reactive Programming)
- **Database**: MongoDB
- **Additional Tools**: Lombok

---

## ✅ Project Purpose
This microservice is designed to efficiently manage student data and classroom enrollments, providing a robust API for student management operations.

---

## 🛠️ Project Structure

```
src/main/java/pe/edu/vallegrande/msvstudents/
├── MsvStudentsApplication.java
├── application
│   └── service
│       ├── ClassroomStudentService.java
│       ├── StudentService.java
│       └── impl
│           ├── ClassroomStudentServiceImpl.java
│           └── StudentServiceImpl.java
├── domain
│   ├── enums
│   │   ├── DocumentType.java
│   │   ├── Gender.java
│   │   └── Status.java
│   └── model
│       ├── ClassroomStudent.java
│       └── Student.java
└── infrastructure
    ├── config
    │   └── WebConfig.java
    ├── dto
    │   ├── request
    │   │   ├── ClassroomStudentRequest.java
    │   │   └── StudentRequest.java
    │   └── response
    │       ├── ClassroomStudentResponse.java
    │       └── StudentResponse.java
    ├── exception
    │   └── GlobalExceptionHandler.java
    ├── repository
    │   ├── ClassroomStudentRepository.java
    │   ├── StudentRepository.java
    │   └── impl
    │       ├── ClassroomStudentRepositoryImpl.java
    │       └── StudentRepositoryImpl.java
    └── rest
        ├── ClassroomStudentController.java
        └── StudentController.java
```

## 🚀 API Endpoints

### Students (`/api/v1/students`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all students |
| GET | `/{id}` | Get student by ID |
| POST | `/` | Create new student |
| PUT | `/{id}` | Update existing student |
| DELETE | `/{id}` | Delete student (change status to inactive) |
| GET | `/institution/{institutionId}` | Get students by institution ID |
| GET | `/status/{status}` | Get students by status |
| GET | `/gender/{gender}` | Get students by gender |
| PUT | `/{id}/restore` | Restore deleted student |

### Enrollments (`/api/v1/classroom-students`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all enrollments |
| GET | `/{id}` | Get enrollment by ID |
| POST | `/` | Create new enrollment |
| PUT | `/{id}` | Update existing enrollment |
| DELETE | `/{id}` | Delete enrollment (change status to inactive) |
| GET | `/student/{studentId}` | Get enrollments by student ID |
| GET | `/classroom/{classroomId}` | Get enrollments by classroom ID |
| GET | `/status/{status}` | Get enrollments by status |
| GET | `/year/{year}` | Get enrollments by year |
| GET | `/period/{period}` | Get enrollments by period |
| GET | `/year/{year}/period/{period}` | Get enrollments by year and period |
| PUT | `/{id}/restore` | Restore deleted enrollment |

## 📝 Data Models

### StudentRequest
```json
{
    "institutionId": "string",
    "firstName": "string",
    "lastName": "string",
    "documentType": "DNI | PASSPORT | FOREIGN_CARD | OTHERS",
    "documentNumber": "string",
    "gender": "M | F",
    "birthDate": "YYYY-MM-DD",
    "address": "string",
    "phone": "string",
    "email": "string",
    "nameQr": "string"
}
```

### ClassroomStudentRequest
```json
{
    "classroomId": "string",
    "studentId": "string",
    "enrollmentYear": "string",
    "enrollmentPeriod": "string"
}
```

## 🔍 Enumerations

### Status
- `ACTIVE`: "A" - Active record
- `INACTIVE`: "I" - Inactive record

### Gender
- `MALE`: "M" - Male
- `FEMALE`: "F" - Female

### Document Type
- `DNI`: "DNI" - National Identity Document
- `PASSPORT`: "PASSPORT"
- `FOREIGN_CARD`: "FOREIGN_CARD" - Foreign Resident Card
- `OTHERS`: "OTHERS" - Other document types

## ⚙️ CORS Configuration

The microservice is configured to allow CORS requests from any origin (*) with HTTP methods GET, POST, PUT, DELETE, and OPTIONS.

## 🛡️ Error Handling

The system includes a global exception handler that provides consistent responses for:

- Resource not found (404)
- Validation errors (400)
- Internal server errors (500)

## 📊 Record Status

Records (students and enrollments) use the following statuses:

- "A": Active
- "I": Inactive

## 🔄 Version Control

This project is maintained in two remote repositories: GitHub and GitLab:
- GitHub: https://github.com/Omarrivv/vg-ms-students.git
- GitLab: https://gitlab.com/vallegrande/as231s5_prs2/vg-ms-students.git

### Remote Status

```bash
# View configured remotes
git remote -v

# Expected output:
github  https://github.com/Omarrivv/vg-ms-students.git (fetch)
github  https://github.com/Omarrivv/vg-ms-students.git (push)
origin  https://gitlab.com/vallegrande/as231s5_prs2/vg-ms-students.git (fetch)
origin  https://gitlab.com/vallegrande/as231s5_prs2/vg-ms-students.git (push)
```

### Pushing Changes to Both Repositories

1. Save local changes:
```bash
git status
git add .
git commit -m "description of changes"
```

2. Push to GitLab (origin):
```bash
git pull origin main
git push origin main
```

3. Push to GitHub:
```bash
git pull github main
git push github main
```

### Useful Commands

```bash
# View commit history
git log --oneline --graph --all

# Check branch status
git status

# View differences before committing
git diff

# Undo changes in a file before committing
git checkout -- filename

# Create a new branch
git checkout -b branch-name

# Switch branches
git checkout branch-name
```

### Conflict Resolution

If conflicts arise when pulling from any repository:

1. Identify conflicted files (shown in red in git status)

2. Open conflicted files and look for conflict markers:
```
<<<<<<< HEAD
your local changes
=======
remote repository changes
>>>>>>> branch-name
```

3. Edit the file to keep the correct code and remove conflict markers

4. Add resolved files:
```bash
git add .
```

5. Complete the merge:
```bash
git commit -m "resolve merge conflicts"
```

6. Continue pushing to the corresponding repository

### Recommendations

1. Always pull before starting work:
```bash
git pull origin main
git pull github main
```

2. Create small, descriptive commits

3. If in doubt about repository status:
```bash
# Check current status
git status

# View history
git log --oneline
```

4. If you need to undo the last commit (but keep changes):
```bash
git reset --soft HEAD~1
```

---

## 💡 Contributing
Please read our contribution guidelines before submitting pull requests.

## 📞 Support
For support, please open an issue in the repository or contact the development team.

---

**Thank you for contributing to this project!** 
👍 *Let's build something great together.*
