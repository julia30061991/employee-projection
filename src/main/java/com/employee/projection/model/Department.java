package com.employee.projection.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @Column(name = "department_id", columnDefinition = "INT", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int departmentId;
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;
}
