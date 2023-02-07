package com.pack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.dto.EmployeeDto;
import com.pack.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee/api")
public class EmployeeController {

	private EmployeeService service;

	@PostMapping("/save")
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto dto) {
		EmployeeDto dtoResponse = service.saveEmployee(dto);
		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
		EmployeeDto dtoResponse = service.getEmployeeById(id);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

}
