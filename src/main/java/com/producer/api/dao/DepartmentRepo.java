package com.producer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producer.api.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

	Department findByName(String name);
}
