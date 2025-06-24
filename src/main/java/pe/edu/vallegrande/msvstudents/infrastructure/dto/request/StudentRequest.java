package pe.edu.vallegrande.msvstudents.infrastructure.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentRequest {
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