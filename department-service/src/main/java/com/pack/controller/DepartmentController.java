package com.pack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.dto.DepartmentDto;
import com.pack.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/department/api")
public class DepartmentController {
	private DepartmentService service;

	@PostMapping("/save")
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto dto) {
		DepartmentDto dtoResponse = service.saveDepartment(dto);
		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<DepartmentDto> saveDepartment(@PathVariable("id") Long id) {
		DepartmentDto dtoResponse = service.getDepartmentById(id);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

}
