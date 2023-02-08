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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;

	// private RestTemplate restTemplate;

	private WebClient webClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto dto) {
		Employee employee = mapToEntity(dto);
		Employee savedEmployee = repository.save(employee);
		return mapToDto(savedEmployee);
	}

	@Override
	public ApiResponse getEmployeeById(Long id) {
		Employee employee = repository.findById(id).get();

		// ResponseEntity<DepartmentDto> dtoResponse = restTemplate.getForEntity(
		// "http://localhost:9091/department/api/get/" + employee.getDepartmentCode(),
		// DepartmentDto.class);

		DepartmentDto dtoResponse = webClient.get()
				.uri("http://localhost:9091/department/api/get/" + employee.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();

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
}
