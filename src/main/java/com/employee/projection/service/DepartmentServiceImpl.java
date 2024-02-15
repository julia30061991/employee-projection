package com.employee.projection.service;

import com.employee.projection.model.Department;
import com.employee.projection.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department addDepartment(String name) throws DepartmentException {
        if (name == null || name.isEmpty()) {
            throw new DepartmentException("Field \"Name\" cannot ba empty");
        } else {
            Department department = new Department();
            department.setName(name);
            departmentRepository.save(department);
            return department;
        }
    }

    @Override
    public void deleteDepartment(int id) throws DepartmentException {
        Department department = departmentRepository.getDepartmentByDepartmentId(id);
        if (department != null) {
            departmentRepository.delete(department);
        } else {
            throw new DepartmentException("Department with id " + id + " does not found");
        }
    }

    @Override
    public Department updateDepartment(int id, String name) throws DepartmentException {
        Department department = departmentRepository.getDepartmentByDepartmentId(id);
        if (department != null) {
            if (name == null || name.isEmpty()) {
                department.setName(department.getName());
            } else department.setName(name);
        } else {
            throw new DepartmentException("Department with id \" + id + \" does not found");
        }
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Department getDepartmentInfo(int id) throws DepartmentException {
        Department department = departmentRepository.getDepartmentByDepartmentId(id);
        if (department != null) {
            return department;
        } else {
            throw new DepartmentException("Department with id \" + id + \" does not found");
        }
    }
}