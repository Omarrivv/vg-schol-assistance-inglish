package pe.edu.vallegrande.msvstudents.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.msvstudents.application.service.StudentService;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.StudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.StudentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Flux<StudentResponse> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<StudentResponse> findById(@PathVariable String id) {
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StudentResponse> save(@RequestBody StudentRequest request) {
        return studentService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<StudentResponse> update(@PathVariable String id, @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return studentService.delete(id);
    }

    @GetMapping("/institution/{institutionId}")
    public Flux<StudentResponse> findByInstitutionId(@PathVariable String institutionId) {
        return studentService.findByInstitutionId(institutionId);
    }

    @GetMapping("/status/{status}")
    public Flux<StudentResponse> findByStatus(@PathVariable String status) {
        return studentService.findByStatus(status);
    }

    @GetMapping("/gender/{gender}")
    public Flux<StudentResponse> findByGender(@PathVariable String gender) {
        return studentService.findByGender(gender);
    }

    @PutMapping("/{id}/restore")
    public Mono<StudentResponse> restore(@PathVariable String id) {
        return studentService.restore(id);
    }
} 