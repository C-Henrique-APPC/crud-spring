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
import org.springframework.web.bind.annotation.RestController;

import com.curso.crudspring.model.Courses;
import com.curso.crudspring.repository.CoursesRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
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
    public ResponseEntity<Courses> create(@RequestBody @Valid Courses entity) {
        Courses course = this.repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courses> findById(@PathVariable("id") @NotNull @Positive Long id) {
        return this.repository.findById(id).map(record -> ResponseEntity.status(HttpStatus.OK).body(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Courses> update(@PathVariable("id") @NotNull @Positive Long id,
            @RequestBody @Valid Courses courses) {
        return repository.findById(id).map(
                recordFound -> {
                    recordFound.setName(courses.getName());
                    recordFound.setCategory(courses.getCategory());
                    Courses updated = repository.save(recordFound);
                    return ResponseEntity.status(HttpStatus.OK).body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Positive Long id) {
        return repository.findById(id).map(
                rercordFound -> {
                    this.repository.delete(rercordFound);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
