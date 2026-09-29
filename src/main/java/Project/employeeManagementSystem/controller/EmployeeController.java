package Project.employeeManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.employeeManagementSystem.dto.EmployeeRequestDTO;
import Project.employeeManagementSystem.dto.EmployeeResponseDTO;
import Project.employeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;




@RestController 
@RequestMapping("/api/employees") 
public class EmployeeController {
    
    private  EmployeeService service ;

    public EmployeeController (EmployeeService employeeService){
        this.service = employeeService; 
    }

   @PostMapping("/add")
public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {   
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createEmployee(employeeRequestDTO));
}

    @GetMapping("/allEmployee")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeesById(id));
    }

  @GetMapping("/department/{department}")
   public ResponseEntity<List<EmployeeResponseDTO>> getByDepartment(@PathVariable String department) {
    return ResponseEntity.ok(service.getEmployeesByDepartment(department));
}

        @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id,
                                    @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(service.updateEmployees(id, employeeRequestDTO));
    }

      @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployees(id);
        return ResponseEntity.noContent()
        .build();
    }
    
}
