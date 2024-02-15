package com.employee.projection.service;

import com.employee.projection.model.Employee;
import com.employee.projection.model.EmployeeProjection;

import java.util.List;

public interface EmployeeService {

    Employee addNewEmployee(String firstName, String lastName, String position,
                            double salary, int departmentId) throws EmployeeDataException;

    Employee getEmployeeInfo(int id) throws EmployeeDataException;

    Employee updateEmployee(int id, String firstName, String lastName, String position,
                            double salary, int departmentId) throws EmployeeDataException;

    void deleteEmployee(int id) throws EmployeeDataException;

    List<EmployeeProjection> getEmployeeView(String lastName) throws EmployeeDataException;
}