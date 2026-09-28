package Project.employeeManagementSystem.controller;

import java.util.List;

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
public EmployeeResponseDTO createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {   
    return service.createEmployee(employeeRequestDTO);
}

    @GetMapping("/allEmployee")
    public List<EmployeeResponseDTO> getAllEmployees() {
        return service.getAllEmployees();
    }
    
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeesById(@PathVariable Long id) {
        return service.getEmployeesById(id);
    }

  @GetMapping("/department/{department}")
   public List<EmployeeResponseDTO> getByDepartment(@PathVariable String department) {
    return service.getEmployeesByDepartment(department);
}

        @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id,
                                    @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return service.updateEmployees(id, employeeRequestDTO);
    }

      @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployees(id);
        return "Employee Deleted Successfully";
    }
    
}
