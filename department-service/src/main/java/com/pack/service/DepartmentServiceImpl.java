package com.pack.service;

import org.springframework.stereotype.Service;

import com.pack.dto.DepartmentDto;
import com.pack.entity.Department;
import com.pack.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentRepository repository;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto dto) {
		Department department = mapToEntity(dto);
		Department savedDepartment = repository.save(department);
		return mapToDto(savedDepartment);
	}

	@Override
	public DepartmentDto getDepartmentByCode(String code) {
		Department department = repository.findByDepartmentCode(code);
		return mapToDto(department);
	}

	private DepartmentDto mapToDto(Department department) {
		DepartmentDto dto = new DepartmentDto();
		dto.setId(department.getId());
		dto.setDepartmentName(department.getDepartmentName());
		dto.setDepartmentDescription(department.getDepartmentDescription());
		dto.setDepartmentCode(department.getDepartmentCode());
		return dto;
	}

	private Department mapToEntity(DepartmentDto dto) {
		Department department = new Department();
		department.setId(dto.getId());
		department.setDepartmentName(dto.getDepartmentName());
		department.setDepartmentDescription(dto.getDepartmentDescription());
		department.setDepartmentCode(dto.getDepartmentCode());
		return department;
	}
}
