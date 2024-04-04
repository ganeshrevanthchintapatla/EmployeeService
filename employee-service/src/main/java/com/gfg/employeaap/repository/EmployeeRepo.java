package com.gfg.employeaap.repository;
 
import com.gfg.employeaap.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
 
}