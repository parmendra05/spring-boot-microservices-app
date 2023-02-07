package com.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
