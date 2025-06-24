package pe.edu.vallegrande.msvstudents.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "classroom_students")
public class ClassroomStudent {
    @Id
    private String id;
    private String classroomId;
    private String studentId;
    private LocalDate enrollmentDate;
    private String status;
    
    // Additional fields to ensure complete data retrieval
    private String enrollmentYear;
    private String enrollmentPeriod;
}