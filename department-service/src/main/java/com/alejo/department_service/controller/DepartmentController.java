package com.alejo.department_service.controller;


import com.alejo.department_service.model.Department;
import com.alejo.department_service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")

public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return ResponseEntity.ok(departments);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Department department) {
        departmentRepository.create(department);
        return ResponseEntity.ok().build();
    }




}
