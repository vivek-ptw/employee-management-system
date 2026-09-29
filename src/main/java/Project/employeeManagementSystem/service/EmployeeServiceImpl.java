package Project.employeeManagementSystem.service;

import java.util.List;

import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper ;
    

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDto) {
        if (employeeRepository.existsByEmail(requestDto.getEmail())){
            throw new DuplicateResourceException("Employee with this email already exists");
        }
            

    Employees newEmployee = modelMapper.map(requestDto,Employees.class);
    Employees savedEmployee = employeeRepository.save(newEmployee);

   return modelMapper.map(savedEmployee,EmployeeResponseDTO.class);
    }



    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employees> employeesList = employeeRepository.findAll();
         return employeesList.stream()
         .map(employeeList -> modelMapper.map(employeeList,EmployeeResponseDTO.class))
         .toList();

    }



    @Override
    public EmployeeResponseDTO getEmployeesById(Long id) {

        Employees employee = employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id:"+id));
         return modelMapper.map(employee,EmployeeResponseDTO.class);
    }



    @Override
    public EmployeeResponseDTO updateEmployees(Long id, EmployeeRequestDTO requestDto) {

        Employees existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        modelMapper.map(requestDto,existingEmployee );
         Employees updatedEmployee = employeeRepository.save(existingEmployee);

        return modelMapper.map(updatedEmployee, EmployeeResponseDTO.class);
    }


    
    @Override
    public void deleteEmployees(Long id) {
        if(!employeeRepository.existsById(id)){
             throw new IllegalArgumentException("Student does not exists by id: "+id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
public List<EmployeeResponseDTO> getEmployeesByDepartment(String department) {
    List<Employees> employeesList = employeeRepository.findByDepartment(department);
     return employeesList.stream()
    .map(employeeList -> modelMapper.map(employeeList, EmployeeResponseDTO.class)).toList();

    }
}
