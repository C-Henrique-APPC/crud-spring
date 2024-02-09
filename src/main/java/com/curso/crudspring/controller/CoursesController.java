package com.curso.crudspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

}
