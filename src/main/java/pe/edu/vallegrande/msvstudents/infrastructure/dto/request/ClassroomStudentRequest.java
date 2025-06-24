package pe.edu.vallegrande.msvstudents.infrastructure.dto.request;

import lombok.Data;

@Data
public class ClassroomStudentRequest {
    private String classroomId;
    private String studentId;
    private String enrollmentYear;
    private String enrollmentPeriod;
} 