package com.employee.projection.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @Column(name = "employee_id", columnDefinition = "INT", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    @Column(name = "firstName", columnDefinition = "VARCHAR(255)")
    private String firstName;
    @Column(name = "lastName", columnDefinition = "VARCHAR(255)")
    private String lastName;
    @Column(name = "position", columnDefinition = "VARCHAR(255)")
    private String position;
    @Column(name = "salary", columnDefinition = "NUMERIC")
    private double salary;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    private Department department;

    public Employee(String firstName, String lastName, String position, double salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}