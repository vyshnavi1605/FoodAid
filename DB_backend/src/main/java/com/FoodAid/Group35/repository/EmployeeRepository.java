package com.FoodAid.Group35.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodAid.Group35.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}