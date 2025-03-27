package com.mehmetali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mehmetali.entity.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long >{

}
