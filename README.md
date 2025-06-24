# Students Microservice – Technical Overview

## 🔧 Project Stack
- **Backend**: Java 17 (Spring Boot, Spring WebFlux)
- **Database**: MongoDB
- **Other**: Lombok

---

## ✅ Project Purpose
This microservice manages student records and classroom enrollments for Valle Grande, providing a reactive, scalable backend for educational applications.

---

## 🛠️ Setup Instructions (Imperatives)
1. **Clone** the repository:  
   `git clone https://github.com/Omarrivv/vg-ms-students.git`
2. **Navigate** into the project directory:  
   `cd vg-ms-students`
3. **Run** the Spring Boot app:  
   `./mvnw spring-boot:run`  
   or  
   `mvn spring-boot:run`  

---

## 🧩 How to Use the API (Advice with “should”)
- You **should** access endpoints under `/api/v1/students` and `/api/v1/classroom-students`.
- You **should** use a REST client (e.g., Postman) to test the endpoints.
- You **should** provide valid JSON bodies as described in the Data Models section.

---

## 🎯 Future Plans (Advice & Suggestions)
- We **should** add authentication and authorization for sensitive endpoints.
- We **should** implement pagination and filtering for large datasets.
- We **should** add OpenAPI/Swagger documentation for easier integration.

---

## 📁 Repository Structure
```text
src/main/java/pe/edu/vallegrande/msvstudents/
├── MsvStudentsApplication.java
├── application/
│   └── service/
│       ├── ClassroomStudentService.java
│       ├── StudentService.java
│       └── impl/
│           ├── ClassroomStudentServiceImpl.java
│           └── StudentServiceImpl.java
├── domain/
│   ├── enums/
│   │   ├── DocumentType.java
│   │   ├── Gender.java
│   │   └── Status.java
│   └── model/
│       ├── ClassroomStudent.java
│       └── Student.java
└── infrastructure/
    ├── config/
    │   └── WebConfig.java
    ├── dto/
    │   ├── request/
    │   │   ├── ClassroomStudentRequest.java
    │   │   └── StudentRequest.java
    │   └── response/
    │       ├── ClassroomStudentResponse.java
    │       └── StudentResponse.java
    ├── exception/
    │   └── GlobalExceptionHandler.java
    ├── repository/
    │   ├── ClassroomStudentRepository.java
    │   ├── StudentRepository.java
    │   └── impl/
    │       ├── ClassroomStudentRepositoryImpl.java
    │       └── StudentRepositoryImpl.java
    └── rest/
        ├── ClassroomStudentController.java
        └── StudentController.java
```

---

## 🗂️ API Endpoints

### Students (`/api/v1/students`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/`      | Get all students |
| GET    | `/{id}`  | Get student by ID |
| POST   | `/`      | Create new student |
| PUT    | `/{id}`  | Update existing student |
| DELETE | `/{id}`  | Soft-delete student (set status to inactive) |
| GET    | `/institution/{institutionId}` | Get students by institution ID |
| GET    | `/status/{status}` | Get students by status |
| GET    | `/gender/{gender}` | Get students by gender |
| PUT    | `/{id}/restore` | Restore a deleted student |

### Enrollments (`/api/v1/classroom-students`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/`      | Get all enrollments |
| GET    | `/{id}`  | Get enrollment by ID |
| POST   | `/`      | Create new enrollment |
| PUT    | `/{id}`  | Update enrollment |
| DELETE | `/{id}`  | Soft-delete enrollment (set status to inactive) |
| GET    | `/student/{studentId}` | Get enrollments by student ID |
| GET    | `/classroom/{classroomId}` | Get enrollments by classroom ID |
| GET    | `/status/{status}` | Get enrollments by status |
| GET    | `/year/{year}` | Get enrollments by year |
| GET    | `/period/{period}` | Get enrollments by period |
| GET    | `/year/{year}/period/{period}` | Get enrollments by year and period |
| PUT    | `/{id}/restore` | Restore a deleted enrollment |

---

## 📄 Data Models

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

---

## 🏷️ Enumerations

- **Status**:  
  - `ACTIVE`: "A" (Active)  
  - `INACTIVE`: "I" (Inactive)
- **Gender**:  
  - `MALE`: "M"  
  - `FEMALE`: "F"
- **DocumentType**:  
  - `DNI`: "DNI"  
  - `PASSPORT`: "PASSPORT"  
  - `FOREIGN_CARD`: "FOREIGN_CARD"  
  - `OTHERS`: "OTHERS"

---

## 🌐 CORS Configuration
CORS is enabled for all origins (`*`) and HTTP methods: GET, POST, PUT, DELETE, OPTIONS.

---

## ⚠️ Error Handling
A global exception handler provides consistent responses for:
- Resource not found (404)
- Validation errors (400)
- Internal server errors (500)

---

## 🔄 Record Status
All records (students and enrollments) use:
- "A": Active
- "I": Inactive

---

## 🔗 Version Control & Remotes

This project uses two remotes:
- GitHub: https://github.com/Omarrivv/vg-ms-students.git
- GitLab: https://gitlab.com/vallegrande/as231s5_prs2/vg-ms-students.git

**To push changes to both remotes:**
```bash
git add .
git commit -m "your commit message"
git pull origin main
git push origin main
git pull github main
git push github main
```

---

## 💡 Best Practices & Tips
- You **should** pull from both remotes before starting work.
- You **should** write small, descriptive commits.
- You **should** document new endpoints and changes.
- You **should** use `git status` and `git log --oneline` to track your work.
- You **should** resolve merge conflicts carefully and test after merging.

---

## 📞 Questions & Support
If you need help:
- **Open** an issue in this repository.
- **Tag** `@project-lead` for urgent issues.
- **Join** our group chat for real-time support.

---

**Thank you for contributing!**  
👍 *Let’s build something great together!*
