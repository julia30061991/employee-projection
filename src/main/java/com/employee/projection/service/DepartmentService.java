package com.employee.projection.service;

import com.employee.projection.model.Department;

public interface DepartmentService {

    Department addDepartment(String name) throws DepartmentException;

    void deleteDepartment(int id) throws DepartmentException;

    Department updateDepartment(int id, String name) throws DepartmentException;

    Department getDepartmentInfo(int id) throws DepartmentException;
}
