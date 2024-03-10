package com.curso.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.curso.crudspring.exception.RecordNotFoudException;
import com.curso.crudspring.model.Courses;
import com.curso.crudspring.repository.CoursesRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class CoursesService {

    private final CoursesRepository repository;

    public List<Courses> list() {
        return this.repository.findAll();
    }

    public Courses create(@Valid Courses entity) {
        return this.repository.save(entity);
    }

    public Courses findById(@NotNull @Positive Long id) {
        return this.repository.findById(id).orElseThrow(() -> new RecordNotFoudException(id));

    }

    public Courses update(@NotNull @Positive Long id,
            @RequestBody @Valid Courses courses) {
        return this.repository.findById(id).map(
                recordFound -> {
                    recordFound.setName(courses.getName());
                    recordFound.setCategory(courses.getCategory());

                    return this.repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoudException(id));

    }

    public void delete(@NotNull @Positive Long id) {
        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new RecordNotFoudException(id)));
    }
}
