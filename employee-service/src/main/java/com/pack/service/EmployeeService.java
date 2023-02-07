package com.pack.service;

import com.pack.dto.ApiResponse;
import com.pack.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto dto);

	ApiResponse getEmployeeById(Long id);

}
