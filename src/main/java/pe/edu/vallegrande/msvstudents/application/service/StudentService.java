package pe.edu.vallegrande.msvstudents.application.service;

import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.StudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.StudentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<StudentResponse> findAll();
    Mono<StudentResponse> findById(String id);
    Mono<StudentResponse> save(StudentRequest request);
    Mono<StudentResponse> update(String id, StudentRequest request);
    Mono<Void> delete(String id);
    Flux<StudentResponse> findByInstitutionId(String institutionId);
    Flux<StudentResponse> findByStatus(String status);
    Flux<StudentResponse> findByGender(String gender);
    Mono<StudentResponse> restore(String id);
} 