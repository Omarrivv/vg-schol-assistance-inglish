package pe.edu.vallegrande.msvstudents.infrastructure.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.domain.model.Student;
import reactor.core.publisher.Flux;
import java.time.LocalDate;
import java.util.List;
import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "pe.edu.vallegrande.msvstudents.infrastructure.repository")
public class MongoConfig {

    @Bean
    public CommandLineRunner initData(ReactiveMongoTemplate mongoTemplate) {
        return args -> {
            // Solo insertar datos de ejemplo si las colecciones están vacías
            mongoTemplate.count(new Query(), Student.class)
                .flatMap(count -> {
                    if (count == 0) {
                        // Crear estudiantes
                        Student student1 = new Student();
                        student1.setInstitutionId("1");
                        student1.setFirstName("Juan");
                        student1.setLastName("Gómez");
                        student1.setDocumentType("DNI");
                        student1.setDocumentNumber("78901234");
                        student1.setGender("M");
                        student1.setBirthDate(LocalDate.of(2015, 3, 15));
                        student1.setAddress("Jr. Los Pinos 123");
                        student1.setPhone("912345678");
                        student1.setEmail("juangomez@mail.com");
                        student1.setNameQr("Juan_Gomez_78901234");
                        student1.setStatus("A");

                        Student student2 = new Student();
                        student2.setInstitutionId("1");
                        student2.setFirstName("Lucía");
                        student2.setLastName("Martínez");
                        student2.setDocumentType("DNI");
                        student2.setDocumentNumber("78901235");
                        student2.setGender("F");
                        student2.setBirthDate(LocalDate.of(2014, 5, 20));
                        student2.setAddress("Av. Los Jardines 456");
                        student2.setPhone("912345679");
                        student2.setEmail("luciamartinez@mail.com");
                        student2.setNameQr("Lucia_Martinez_78901235");
                        student2.setStatus("A");

                        Student student3 = new Student();
                        student3.setInstitutionId("1");
                        student3.setFirstName("Pablo");
                        student3.setLastName("Torres");
                        student3.setDocumentType("DNI");
                        student3.setDocumentNumber("78901236");
                        student3.setGender("M");
                        student3.setBirthDate(LocalDate.of(2015, 7, 10));
                        student3.setAddress("Calle Los Olivos 789");
                        student3.setPhone("912345680");
                        student3.setEmail("pablotorres@mail.com");
                        student3.setNameQr("Pablo_Torres_78901236");
                        student3.setStatus("A");

                        return mongoTemplate.insertAll(List.of(student1, student2, student3))
                            .collectList()
                            .flatMapMany(students -> {
                                ClassroomStudent cs1 = new ClassroomStudent();
                                cs1.setClassroomId("1");
                                cs1.setStudentId(students.get(0).getId());
                                cs1.setEnrollmentDate(LocalDate.of(2023, 3, 1));
                                cs1.setStatus("A");

                                ClassroomStudent cs2 = new ClassroomStudent();
                                cs2.setClassroomId("1");
                                cs2.setStudentId(students.get(1).getId());
                                cs2.setEnrollmentDate(LocalDate.of(2023, 3, 1));
                                cs2.setStatus("A");

                                ClassroomStudent cs3 = new ClassroomStudent();
                                cs3.setClassroomId("2");
                                cs3.setStudentId(students.get(2).getId());
                                cs3.setEnrollmentDate(LocalDate.of(2023, 3, 1));
                                cs3.setStatus("A");

                                return mongoTemplate.insertAll(List.of(cs1, cs2, cs3));
                            })
                            .then();
                    } else {
                        return Mono.empty();
                    }
                })
                .block();
        };
    }
} 