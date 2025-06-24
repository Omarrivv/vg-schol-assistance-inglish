package pe.edu.vallegrande.msvstudents.infrastructure.dto.response;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDate;

@Data
@Builder
public class ClassroomStudentResponse {
    private String id;
    private String classroomId;
    private String studentId;
    private LocalDate enrollmentDate;
    private String enrollmentYear;
    private String enrollmentPeriod;
    private String status;
} 