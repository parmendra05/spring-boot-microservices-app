package com.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
