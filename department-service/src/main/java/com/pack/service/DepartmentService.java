package com.pack.service;

import com.pack.dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto saveDepartment(DepartmentDto dto);

	DepartmentDto getDepartmentById(Long id);

}
