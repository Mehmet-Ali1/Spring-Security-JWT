package com.mehmetali.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehmetali.dto.DtoDepartment;
import com.mehmetali.dto.DtoEmployee;
import com.mehmetali.entity.Employee;
import com.mehmetali.entity.department;
import com.mehmetali.repository.EmployeeRepository;
import com.mehmetali.service.IEmployeeService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DtoEmployee findEmployeeById(Long id) {
		DtoEmployee dtoEmployee = new DtoEmployee();
		DtoDepartment dtoDepartment = new DtoDepartment();
		Optional<Employee> optinal = employeeRepository.findById(id);

		if (optinal.isEmpty()) {
			return null;
		}
		Employee employee = optinal.get();
		department department = employee.getDepartment();
		BeanUtils.copyProperties(employee, dtoEmployee);
		BeanUtils.copyProperties(department, dtoDepartment);
		
		dtoEmployee.setDtoDepartment(dtoDepartment);
		return dtoEmployee;
	}

}
