package com.employee.projection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.employee.projection.repository")
@ComponentScan({"com.employee.projection.controller", "com.employee.projection.service"})
@EntityScan("com.employee.projection.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        }
}