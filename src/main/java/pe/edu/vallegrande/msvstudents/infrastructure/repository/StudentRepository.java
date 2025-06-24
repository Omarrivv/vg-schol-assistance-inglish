package pe.edu.vallegrande.msvstudents.infrastructure.repository;

import pe.edu.vallegrande.msvstudents.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository {
    Flux<Student> findAll();
    Mono<Student> findById(String id);
    Mono<Student> save(Student student);
    Mono<Void> deleteById(String id);
    Flux<Student> findByInstitutionId(String institutionId);
    Flux<Student> findByStatus(String status);
    Flux<Student> findByGender(String gender);
} 