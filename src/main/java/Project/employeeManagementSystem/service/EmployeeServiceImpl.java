package Project.employeeManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Project.employeeManagementSystem.dto.EmployeeRequestDTO;
import Project.employeeManagementSystem.dto.EmployeeResponseDTO;
import Project.employeeManagementSystem.entity.Employees;
import Project.employeeManagementSystem.exception.DuplicateResourceException;
import Project.employeeManagementSystem.exception.ResourceNotFoundException;
import Project.employeeManagementSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class EmployeeServiceImpl implements EmployeeService{
    
    private final EmployeeRepository employeeRepository;
    
   

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDto) {
        if (employeeRepository.existsByEmail(requestDto.getEmail())){
            throw new DuplicateResourceException("Employee with this email already exists");
        }
            
    

    Employees employee = new  Employees();
    employee.setName(requestDto.getName());
    employee.setEmail(requestDto.getEmail());
    employee.setDepartment(requestDto.getDepartment());
    employee.setSalary(requestDto.getSalary());

    Employees savedEmployee = employeeRepository.save(employee);

    EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

    responseDTO.setId(savedEmployee.getId());
    responseDTO.setName(savedEmployee.getName());
    responseDTO.setEmail(savedEmployee.getEmail());
    responseDTO.setDepartment(savedEmployee.getDepartment());
    responseDTO.setSalary(savedEmployee.getSalary());

    return responseDTO;
    }



    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employees> employeesList = employeeRepository.findAll();
        List<EmployeeResponseDTO> responseList = new ArrayList<>();

        for( Employees emp : employeesList){
            EmployeeResponseDTO dto = new EmployeeResponseDTO();
            dto.setId(emp.getId());
            dto.setName(emp.getName());
            dto.setEmail(emp.getEmail());
            dto.setDepartment(emp.getDepartment());
            dto.setSalary(emp.getSalary());
            responseList.add(dto);
        }
         return responseList;
    }



    @Override
    public EmployeeResponseDTO getEmployeesById(Long id) {

        Employees employee = employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id:"+id));

      EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
      responseDTO.setId(employee.getId());
      responseDTO.setName(employee.getName());
      responseDTO.setEmail(employee.getEmail());
      responseDTO.setDepartment(employee.getDepartment());
      responseDTO.setSalary(employee.getSalary());
      
      return responseDTO;
    }



    @Override
    public EmployeeResponseDTO updateEmployees(Long id, EmployeeRequestDTO requestDto) {

        Employees existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
         existingEmployee.setName(requestDto.getName());
         existingEmployee.setEmail(requestDto.getEmail());
         existingEmployee.setDepartment(requestDto.getDepartment());
         existingEmployee.setSalary(requestDto.getSalary());

         Employees updatedEmployee = employeeRepository.save(existingEmployee);

         EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
 
         responseDTO.setId(updatedEmployee.getId());
         responseDTO.setName(updatedEmployee.getName());
         responseDTO.setEmail(updatedEmployee.getEmail());
         responseDTO.setDepartment(updatedEmployee.getDepartment());
         responseDTO.setSalary(updatedEmployee.getSalary());

        return responseDTO;
    }


    
    @Override
    public void deleteEmployees(Long id) {
        Employees employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
            employeeRepository.delete(employee);
    }

    @Override
public List<EmployeeResponseDTO> getEmployeesByDepartment(String department) {
    List<Employees> employeesList = employeeRepository.findByDepartment(department);
     List<EmployeeResponseDTO> responseList = new ArrayList<>();

     for(Employees emp : employeesList){
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setDepartment(emp.getDepartment());
        dto.setSalary(emp.getSalary());
        responseList.add(dto);
     }
   return responseList;
}

  
}
