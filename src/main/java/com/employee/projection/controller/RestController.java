package com.employee.projection.controller;

import com.employee.projection.model.Department;
import com.employee.projection.model.Employee;
import com.employee.projection.model.EmployeeProjection;
import com.employee.projection.service.DepartmentException;
import com.employee.projection.service.DepartmentServiceImpl;
import com.employee.projection.service.EmployeeDataException;
import com.employee.projection.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private final EmployeeServiceImpl service;
    @Autowired
    private final DepartmentServiceImpl departmentService;

    public RestController(EmployeeServiceImpl service, DepartmentServiceImpl departmentService) {
        this.service = service;
        this.departmentService = departmentService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeInfo(@PathVariable("id") int id) {
        try {
            Employee employee = service.getEmployeeInfo(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") int id) {
        try {
            service.deleteEmployee(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmployeeDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/employee/create")
    public ResponseEntity<Object> addNewEmployee(@RequestParam String firstName, String lastName,
                                                 String position, double salary, int dep_id) {
        try {
            Employee employee = service.addNewEmployee(firstName, lastName, position, salary, dep_id);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (EmployeeDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/employee/update/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id,
                                                 @RequestParam(required = false) String firstName,
                                                 @RequestParam(required = false) String lastName,
                                                 @RequestParam(required = false) String position,
                                                 @RequestParam(required = false) double salary,
                                                 @RequestParam(required = false) int dep_id) {
        try {
            Employee employee = service.updateEmployee(id, firstName, lastName, position, salary, dep_id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/department/create")
    public ResponseEntity<Object> addNewDepartment(@RequestParam String name) {
        try {
            Department department = departmentService.addDepartment(name);
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } catch (DepartmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/department/update/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable("id") int id,
                                                   @RequestParam(required = false) String name) {
        try {
            Department department = departmentService.updateDepartment(id, name);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (DepartmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity deleteDepartment(@PathVariable("id") int id) {
        try {
            departmentService.deleteDepartment(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DepartmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Object> getDepartmentInfo(@PathVariable("id") int id) {
        try {
            Department department = departmentService.getDepartmentInfo(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (DepartmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employee/view")
    public ResponseEntity<Object> getEmployeeView(@RequestParam String lastName) {
        try {
            List<EmployeeProjection> projection = service.getEmployeeView(lastName);
            return new ResponseEntity <>(projection, HttpStatus.OK);
        } catch (EmployeeDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}