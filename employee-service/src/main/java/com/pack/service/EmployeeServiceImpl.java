package com.pack.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.pack.dto.ApiResponse;
import com.pack.dto.DepartmentDto;
import com.pack.dto.EmployeeDto;
import com.pack.entity.Employee;
import com.pack.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;

	// private RestTemplate restTemplate;

	private API_Client apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto dto) {
		Employee employee = mapToEntity(dto);
		Employee savedEmployee = repository.save(employee);
		return mapToDto(savedEmployee);
	}

	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public ApiResponse getEmployeeById(Long id) {
		Employee employee = repository.findById(id).get();

		// ResponseEntity<DepartmentDto> dtoResponse = restTemplate.getForEntity(
		// "http://localhost:9091/department/api/get/" + employee.getDepartmentCode(),
		// DepartmentDto.class);

		DepartmentDto dtoResponse = apiClient.getDepartmentByCode(employee.getDepartmentCode());

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setEmployeeDto(mapToDto(employee));
		apiResponse.setDepartmentDto(dtoResponse);

		return apiResponse;
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

	public ApiResponse getDefaultDepartment(Long id, Exception exception) {
		Employee employee = repository.findById(id).get();

		DepartmentDto defaultDepartment = new DepartmentDto();
		defaultDepartment.setDepartmentName("R&D Department");
		defaultDepartment.setDepartmentCode("RD00");
		defaultDepartment.setDepartmentDescription("Research And Development Department");

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setEmployeeDto(mapToDto(employee));
		apiResponse.setDepartmentDto(defaultDepartment);
		return apiResponse;
	}
}
