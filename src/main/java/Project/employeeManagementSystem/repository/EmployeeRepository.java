package Project.employeeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Project.employeeManagementSystem.entity.Employees;

public interface EmployeeRepository extends JpaRepository<Employees,Long> {
    
    boolean existsByEmail(String email);
    
    List<Employees> findByDepartment(String department);
}