package pe.edu.vallegrande.msvstudents.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "students")
public class Student {
    @Id
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