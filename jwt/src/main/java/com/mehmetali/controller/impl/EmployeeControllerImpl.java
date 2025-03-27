package com.mehmetali.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehmetali.controller.IEmployeeController;
import com.mehmetali.dto.DtoEmployee;
import com.mehmetali.service.IEmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements IEmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping(path = "/{id}")
	@Override
	public DtoEmployee findEmployeeById(@Valid @NotNull @PathVariable(value = "id") Long id) {
		return employeeService.findEmployeeById(id);
	}

}
