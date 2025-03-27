package com.mehmetali.controller;

import com.mehmetali.dto.DtoEmployee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface IEmployeeController {

	public DtoEmployee findEmployeeById(@Valid @NotNull Long id);
}
