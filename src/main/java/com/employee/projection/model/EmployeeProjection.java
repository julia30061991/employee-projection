package com.employee.projection.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "employeeProjection", types = Employee.class)
public interface EmployeeProjection {

    @Value("#{target.lastName}, #{target.firstName}")
    String getFullName();

    String getPosition();

    Department getDepartment();

    interface Department {
        String getName();
    }
}