package Project.employeeManagementSystem.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
public class EmployeeRequestDTO {
    
     @NotBlank(message = "Name is required")
     @Size(min =3,max =30, message = "name should be of length to 3 to 30 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @Positive(message = "Salary must be positive")
    private double salary;

}
