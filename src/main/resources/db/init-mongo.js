// Crear la base de datos
//use msv_students

// Crear colección de estudiantes
db.createCollection("students")

// Crear índices para la colección students
db.students.createIndex({ "documentNumber": 1 }, { unique: true })
db.students.createIndex({ "institutionId": 1 })
db.students.createIndex({ "status": 1 })
db.students.createIndex({ "gender": 1 })

// Crear colección de matrículas
db.createCollection("classroom_students")

// Crear índices para la colección classroom_students
db.classroom_students.createIndex({ "studentId": 1 })
db.classroom_students.createIndex({ "classroomId": 1 })
db.classroom_students.createIndex({ "status": 1 })

// Insertar datos de ejemplo para estudiantes
db.students.insertMany([
    {
        institutionId: "1",
        firstName: "Juan",
        lastName: "Gómez",
        documentType: "DNI",
        documentNumber: "78901234",
        gender: "M",
        birthDate: ISODate("2015-03-15"),
        address: "Jr. Los Pinos 123",
        phone: "912345678",
        email: "juangomez@mail.com",
        nameQr: "Juan_Gomez_78901234",
        status: "A"
    },
    {
        institutionId: "1",
        firstName: "Lucía",
        lastName: "Martínez",
        documentType: "DNI",
        documentNumber: "78901235",
        gender: "F",
        birthDate: ISODate("2014-05-20"),
        address: "Av. Los Jardines 456",
        phone: "912345679",
        email: "luciamartinez@mail.com",
        nameQr: "Lucia_Martinez_78901235",
        status: "A"
    },
    {
        institutionId: "1",
        firstName: "Pablo",
        lastName: "Torres",
        documentType: "DNI",
        documentNumber: "78901236",
        gender: "M",
        birthDate: ISODate("2015-07-10"),
        address: "Calle Los Olivos 789",
        phone: "912345680",
        email: "pablotorres@mail.com",
        nameQr: "Pablo_Torres_78901236",
        status: "A"
    }
])

// Insertar datos de ejemplo para matrículas
db.classroom_students.insertMany([
    {
        classroomId: "1",
        studentId: "1", // ID de Juan Gómez
        enrollmentDate: ISODate("2023-03-01"),
        status: "A"
    },
    {
        classroomId: "1",
        studentId: "2", // ID de Lucía Martínez
        enrollmentDate: ISODate("2023-03-01"),
        status: "A"
    },
    {
        classroomId: "2",
        studentId: "3", // ID de Pablo Torres
        enrollmentDate: ISODate("2023-03-01"),
        status: "A"
    }
]) 