package pe.edu.vallegrande.msvstudents.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.msvstudents.application.service.ClassroomStudentService;
import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.infrastructure.repository.ClassroomStudentRepository;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.ClassroomStudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.ClassroomStudentResponse;
import pe.edu.vallegrande.msvstudents.infrastructure.exception.ResourceNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClassroomStudentServiceImpl implements ClassroomStudentService {

    private final ClassroomStudentRepository repository;

    @Override
    public Flux<ClassroomStudentResponse> findAll() {
        return repository.findAll()
                .map(this::toResponse);
    }

    @Override
    public Mono<ClassroomStudentResponse> findById(String id) {
        return repository.findById(id)
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }

    @Override
    public Mono<ClassroomStudentResponse> save(ClassroomStudentRequest request) {
        return repository.existsByStudentIdAndStatus(request.getStudentId(), "A")
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("El estudiante ya tiene una matrícula activa"));
                    }
                    ClassroomStudent enrollment = toEntity(request);
                    enrollment.setStatus("A");
                    enrollment.setEnrollmentDate(LocalDate.now());
                    return repository.save(enrollment);
                })
                .map(this::toResponse);
    }

    @Override
    public Mono<ClassroomStudentResponse> update(String id, ClassroomStudentRequest request) {
        return repository.findById(id)
                .flatMap(existingEnrollment -> {
                    ClassroomStudent updatedEnrollment = toEntity(request);
                    updatedEnrollment.setId(id);
                    updatedEnrollment.setStatus(existingEnrollment.getStatus());
                    updatedEnrollment.setEnrollmentDate(existingEnrollment.getEnrollmentDate());
                    return repository.save(updatedEnrollment);
                })
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.findById(id)
                .flatMap(enrollment -> {
                    enrollment.setStatus("I");
                    return repository.save(enrollment);
                })
                .then();
    }

    @Override
    public Mono<ClassroomStudentResponse> restore(String id) {
        return repository.findById(id)
                .flatMap(enrollment -> {
                    enrollment.setStatus("A");
                    return repository.save(enrollment);
                })
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }

    @Override
    public Flux<ClassroomStudentResponse> findByStudentId(String studentId) {
        return repository.findByStudentId(studentId)
                .map(this::toResponse);
    }

    @Override
    public Flux<ClassroomStudentResponse> findByClassroomId(String classroomId) {
        return repository.findByClassroomId(classroomId)
                .map(this::toResponse);
    }

    @Override
    public Flux<ClassroomStudentResponse> findByStatus(String status) {
        return repository.findByStatus(status)
                .map(this::toResponse);
    }

    @Override
    public Flux<ClassroomStudentResponse> findByYear(String year) {
        return repository.findByEnrollmentYear(year)
                .map(this::toResponse);
    }

    @Override
    public Flux<ClassroomStudentResponse> findByPeriod(String period) {
        return repository.findByEnrollmentPeriod(period)
                .map(this::toResponse);
    }

    @Override
    public Flux<ClassroomStudentResponse> findByYearAndPeriod(String year, String period) {
        return repository.findByEnrollmentYear(year)
                .filter(enrollment -> enrollment.getEnrollmentPeriod().equals(period))
                .map(this::toResponse);
    }

    private ClassroomStudent toEntity(ClassroomStudentRequest request) {
        ClassroomStudent enrollment = new ClassroomStudent();
        enrollment.setClassroomId(request.getClassroomId());
        enrollment.setStudentId(request.getStudentId());
        enrollment.setEnrollmentYear(request.getEnrollmentYear());
        enrollment.setEnrollmentPeriod(request.getEnrollmentPeriod());
        return enrollment;
    }

    private ClassroomStudentResponse toResponse(ClassroomStudent enrollment) {
        return ClassroomStudentResponse.builder()
                .id(enrollment.getId())
                .classroomId(enrollment.getClassroomId())
                .studentId(enrollment.getStudentId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .enrollmentYear(enrollment.getEnrollmentYear())
                .enrollmentPeriod(enrollment.getEnrollmentPeriod())
                .status(enrollment.getStatus())
                .build();
    }
} 