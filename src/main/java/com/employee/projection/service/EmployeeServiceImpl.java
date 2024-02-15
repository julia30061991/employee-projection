package com.employee.projection.service;

import com.employee.projection.model.Department;
import com.employee.projection.model.Employee;
import com.employee.projection.model.EmployeeProjection;
import com.employee.projection.repository.DepartmentRepository;
import com.employee.projection.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName, String position,
                                   double salary, int departmentId) throws EmployeeDataException {
        Employee employee = new Employee();
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new EmployeeDataException("Fields \"First name\" and \"Last name\" cannot be empty");
        } else {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
        }
        if (salary < 0) {
            throw new EmployeeDataException("Salary cannot be less than 0"); //установка уровня зп
        } else employee.setSalary(salary);

        Department department = departmentRepository.getDepartmentByDepartmentId(departmentId);
        employee.setDepartment(department);
        employee.setPosition(position);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeInfo(int id) throws EmployeeDataException {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(id);
        if (employee != null) {
            return employee;
        } else {
            throw new EmployeeDataException("Employee with id = " + id + " does not found");
        }
    }

    @Override
    public Employee updateEmployee(int id, String firstName, String lastName, String position,
                                   double salary, int departmentId) throws EmployeeDataException {

        Employee employee = employeeRepository.getEmployeeByEmployeeId(id);
        if (employee != null) {
            if (firstName == null || firstName.isEmpty()) {
                employee.setFirstName(employee.getFirstName());
                employee.setLastName(employee.getLastName());
            } else {
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
            }
            if (position == null || position.isEmpty()) {
                employee.setPosition(employee.getPosition());
            } else employee.setPosition(position);
            if (salary <= 0) {
                employee.setSalary(employee.getSalary());
            } else employee.setSalary(salary);

            employee.setDepartment(departmentRepository.getDepartmentByDepartmentId(departmentId));

        } else {
            throw new EmployeeDataException("Employee with id = \" + id + \" does not found");
        }
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) throws EmployeeDataException {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(id);
        if (employee != null) {
            employeeRepository.delete(employee);
        } else {
            throw new EmployeeDataException("Employee with id = " + id + " does not found");
        }
    }

    @Override
    public List<EmployeeProjection> getEmployeeView(String lastName) throws EmployeeDataException {
        List<EmployeeProjection> projection = employeeRepository.findEmployeeViewByLastName(lastName);
        if (projection != null) {
            return projection;
        } else {
            throw new EmployeeDataException("Employee with lastName = " + lastName + " does not found");
        }
    }
}