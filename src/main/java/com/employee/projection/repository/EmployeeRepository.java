package com.employee.projection.repository;

import com.employee.projection.model.Employee;
import com.employee.projection.model.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer> {

    Employee getEmployeeByEmployeeId(int id);

    List<EmployeeProjection> findEmployeeViewByLastName(String lastName);

}