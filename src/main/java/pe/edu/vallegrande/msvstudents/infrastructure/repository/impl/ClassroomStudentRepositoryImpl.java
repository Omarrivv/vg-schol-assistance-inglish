package pe.edu.vallegrande.msvstudents.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.infrastructure.repository.ClassroomStudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ClassroomStudentRepositoryImpl implements ClassroomStudentRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<ClassroomStudent> findAll() {
        return mongoTemplate.findAll(ClassroomStudent.class);
    }

    @Override
    public Mono<ClassroomStudent> findById(String id) {
        return mongoTemplate.findById(id, ClassroomStudent.class);
    }

    @Override
    public Mono<ClassroomStudent> save(ClassroomStudent classroomStudent) {
        return mongoTemplate.save(classroomStudent);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return findById(id)
                .flatMap(classroomStudent -> {
                    classroomStudent.setStatus("I");
                    return save(classroomStudent);
                })
                .then();
    }

    @Override
    public Flux<ClassroomStudent> findByStudentId(String studentId) {
        return mongoTemplate.find(
            Query.query(Criteria.where("studentId").is(studentId)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByClassroomId(String classroomId) {
        return mongoTemplate.find(
            Query.query(Criteria.where("classroomId").is(classroomId)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByStatus(String status) {
        return mongoTemplate.find(
            Query.query(Criteria.where("status").is(status)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByStudentIdAndStatus(String studentId, String status) {
        return mongoTemplate.find(
            Query.query(Criteria.where("studentId").is(studentId).and("status").is(status)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByClassroomIdAndStatus(String classroomId, String status) {
        return mongoTemplate.find(
            Query.query(Criteria.where("classroomId").is(classroomId).and("status").is(status)),
            ClassroomStudent.class
        );
    }

    @Override
    public Mono<Boolean> existsByStudentIdAndStatus(String studentId, String status) {
        return mongoTemplate.exists(
            Query.query(Criteria.where("studentId").is(studentId).and("status").is(status)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByEnrollmentYear(String year) {
        return mongoTemplate.find(
            Query.query(Criteria.where("enrollmentYear").is(year)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByEnrollmentPeriod(String period) {
        return mongoTemplate.find(
            Query.query(Criteria.where("enrollmentPeriod").is(period)),
            ClassroomStudent.class
        );
    }

    @Override
    public Flux<ClassroomStudent> findByEnrollmentYearAndEnrollmentPeriod(String year, String period) {
        return mongoTemplate.find(
            Query.query(Criteria.where("enrollmentYear").is(year).and("enrollmentPeriod").is(period)),
            ClassroomStudent.class
        );
    }
} 