package com.curso.crudspring.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<Courses> findById(@PathVariable("id") Long id) {
        return this.repository.findById(id).map(record -> ResponseEntity.status(HttpStatus.OK).body(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Courses> update(@PathVariable("id") Long id, @RequestBody Courses courses) {
        return repository.findById(id).map(
                recordFound -> {
                    recordFound.setName(courses.getName());
                    recordFound.setCategory(courses.getCategory());
                    Courses updated = repository.save(recordFound);
                    return ResponseEntity.status(HttpStatus.OK).body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
