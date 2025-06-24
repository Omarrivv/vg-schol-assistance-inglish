package pe.edu.vallegrande.msvstudents.infrastructure.dto.response;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDate;

@Data
@Builder
public class StudentResponse {
    private String id;
    private String institutionId;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String email;
    private String nameQr;
    private String status;
} 