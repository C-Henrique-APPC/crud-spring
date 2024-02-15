package com.curso.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.crudspring.model.Courses;
import com.curso.crudspring.repository.CoursesRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    private final CoursesRepository repository;

    @GetMapping()
    public List<Courses> list() {
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Courses> create(@RequestBody Courses entity) {
        Courses course = this.repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

}
