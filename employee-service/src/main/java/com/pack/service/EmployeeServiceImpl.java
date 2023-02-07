package com.pack.service;

import org.springframework.stereotype.Service;

import com.pack.dto.EmployeeDto;
import com.pack.entity.Employee;
import com.pack.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto dto) {
		Employee employee = mapToEntity(dto);
		Employee savedEmployee = repository.save(employee);
		return mapToDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		Employee employee = repository.findById(id).get();
		return mapToDto(employee);
	}

	private Employee mapToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail());
		employee.setDepartmentCode(dto.getDepartmentCode());
		return employee;
	}

	private EmployeeDto mapToDto(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		dto.setId(employee.getId());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setEmail(employee.getEmail());
		dto.setDepartmentCode(employee.getDepartmentCode());
		return dto;
	}
}
