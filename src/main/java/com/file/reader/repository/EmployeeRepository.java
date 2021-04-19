package com.file.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.reader.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
