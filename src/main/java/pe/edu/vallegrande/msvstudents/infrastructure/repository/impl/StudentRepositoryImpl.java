package pe.edu.vallegrande.msvstudents.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import pe.edu.vallegrande.msvstudents.domain.model.Student;
import pe.edu.vallegrande.msvstudents.infrastructure.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    @Override
    public Mono<Student> findById(String id) {
        return mongoTemplate.findById(id, Student.class);
    }

    @Override
    public Mono<Student> save(Student student) {
        return mongoTemplate.save(student);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return findById(id)
                .flatMap(student -> {
                    student.setStatus("I");
                    return save(student);
                })
                .then();
    }

    @Override
    public Flux<Student> findByInstitutionId(String institutionId) {
        return mongoTemplate.find(
            Query.query(Criteria.where("institutionId").is(institutionId)),
            Student.class
        );
    }

    @Override
    public Flux<Student> findByStatus(String status) {
        return mongoTemplate.find(
            Query.query(Criteria.where("status").is(status)),
            Student.class
        );
    }

    @Override
    public Flux<Student> findByGender(String gender) {
        return mongoTemplate.find(
            Query.query(Criteria.where("gender").is(gender)),
            Student.class
        );
    }
} 