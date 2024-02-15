package com.employee.projection.repository;

import com.employee.projection.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Integer> {

    Department getDepartmentByDepartmentId(int id);
}