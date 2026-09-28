package Project.employeeManagementSystem.service;

import java.util.List;

import Project.employeeManagementSystem.dto.EmployeeRequestDTO;
import Project.employeeManagementSystem.dto.EmployeeResponseDTO;


public interface EmployeeService {
    
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employees);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeesById(Long id);

    EmployeeResponseDTO updateEmployees(Long id, EmployeeRequestDTO employees);

    void deleteEmployees(Long id);
    
    List<EmployeeResponseDTO> getEmployeesByDepartment(String department);
}