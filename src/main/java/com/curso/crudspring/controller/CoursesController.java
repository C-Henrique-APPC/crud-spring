package com.curso.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.crudspring.model.Courses;
import com.curso.crudspring.service.CoursesService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    private final CoursesService service;

    @GetMapping()
    public List<Courses> list() {
        return service.list();
    }

    @PostMapping()
    public ResponseEntity<Courses> create(@RequestBody @Valid Courses entity) {
        Courses course = this.service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("/{id}")
    public Courses findById(@PathVariable("id") @NotNull @Positive Long id) {
        return this.service.findById(id);

    }

    @PutMapping("/{id}")
    public Courses update(@PathVariable("id") @NotNull @Positive Long id,
            @RequestBody @Valid Courses courses) {
        return service.update(id, courses);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        this.service.delete(id);
    }
}
