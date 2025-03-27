package com.mehmetali.service;

import com.mehmetali.dto.DtoEmployee;

public interface IEmployeeService {
	
 public DtoEmployee findEmployeeById(Long id);
}
