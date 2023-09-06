package org.rrhh.department.infrastructure.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentMessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }
}
