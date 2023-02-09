package com.pack.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pack.dto.DepartmentDto;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface API_Client {

	// Written same method like department-controller
	@GetMapping("/department/api/get/{code}")
	public DepartmentDto getDepartmentByCode(@PathVariable("code") String code);
}
