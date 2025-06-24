package pe.edu.vallegrande.msvstudents.application.service;

import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.ClassroomStudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.ClassroomStudentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassroomStudentService {
    Flux<ClassroomStudentResponse> findAll();
    Mono<ClassroomStudentResponse> findById(String id);
    Mono<ClassroomStudentResponse> save(ClassroomStudentRequest request);
    Mono<ClassroomStudentResponse> update(String id, ClassroomStudentRequest request);
    Mono<Void> delete(String id);
    Mono<ClassroomStudentResponse> restore(String id);
    Flux<ClassroomStudentResponse> findByStudentId(String studentId);
    Flux<ClassroomStudentResponse> findByClassroomId(String classroomId);
    Flux<ClassroomStudentResponse> findByStatus(String status);
    Flux<ClassroomStudentResponse> findByYear(String year);
    Flux<ClassroomStudentResponse> findByPeriod(String period);
    Flux<ClassroomStudentResponse> findByYearAndPeriod(String year, String period);
} 