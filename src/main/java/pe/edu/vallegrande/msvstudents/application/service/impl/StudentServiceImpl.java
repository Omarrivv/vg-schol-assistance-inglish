package pe.edu.vallegrande.msvstudents.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.msvstudents.application.service.StudentService;
import pe.edu.vallegrande.msvstudents.domain.model.Student;
import pe.edu.vallegrande.msvstudents.infrastructure.repository.StudentRepository;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.StudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.StudentResponse;
import pe.edu.vallegrande.msvstudents.infrastructure.exception.ResourceNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Flux<StudentResponse> findAll() {
        return studentRepository.findAll()
                .map(this::toResponse);
    }

    @Override
    public Mono<StudentResponse> findById(String id) {
        return studentRepository.findById(id)
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }

    @Override
    public Mono<StudentResponse> save(StudentRequest request) {
        Student student = toEntity(request);
        student.setStatus("A");
        return studentRepository.save(student)
                .map(this::toResponse);
    }

    @Override
    public Mono<StudentResponse> update(String id, StudentRequest request) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    Student updatedStudent = toEntity(request);
                    updatedStudent.setId(id);
                    updatedStudent.setStatus(existingStudent.getStatus());
                    return studentRepository.save(updatedStudent);
                })
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return studentRepository.findById(id)
                .flatMap(student -> {
                    student.setStatus("I");
                    return studentRepository.save(student);
                })
                .then();
    }

    @Override
    public Flux<StudentResponse> findByInstitutionId(String institutionId) {
        return studentRepository.findByInstitutionId(institutionId)
                .map(this::toResponse);
    }

    @Override
    public Flux<StudentResponse> findByStatus(String status) {
        return studentRepository.findByStatus(status)
                .map(this::toResponse);
    }

    @Override
    public Flux<StudentResponse> findByGender(String gender) {
        return studentRepository.findByGender(gender)
                .map(this::toResponse);
    }

    @Override
    public Mono<StudentResponse> restore(String id) {
        return studentRepository.findById(id)
                .flatMap(student -> {
                    student.setStatus("A");
                    return studentRepository.save(student);
                })
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }

    private Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setInstitutionId(request.getInstitutionId());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDocumentType(request.getDocumentType());
        student.setDocumentNumber(request.getDocumentNumber());
        student.setGender(request.getGender());
        student.setBirthDate(request.getBirthDate());
        student.setAddress(request.getAddress());
        student.setPhone(request.getPhone());
        student.setEmail(request.getEmail());
        student.setNameQr(request.getNameQr());
        return student;
    }

    private StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .institutionId(student.getInstitutionId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .documentType(student.getDocumentType())
                .documentNumber(student.getDocumentNumber())
                .gender(student.getGender())
                .birthDate(student.getBirthDate())
                .address(student.getAddress())
                .phone(student.getPhone())
                .email(student.getEmail())
                .nameQr(student.getNameQr())
                .status(student.getStatus())
                .build();
    }
} 