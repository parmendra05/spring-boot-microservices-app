package com.pack.service;

import com.pack.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto dto);

	EmployeeDto getEmployeeById(Long id);

}
