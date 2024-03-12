package com.curso.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.curso.crudspring.dto.CoursesDTO;
import com.curso.crudspring.dto.mapper.CoursesMapper;
import com.curso.crudspring.exception.RecordNotFoudException;
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
    private final CoursesMapper coursesMapper;

    public List<CoursesDTO> list() {
        return this.repository.findAll().stream().map(coursesMapper::toDTO).collect(Collectors.toList());
    }

    public CoursesDTO create(@Valid CoursesDTO entity) {
        return this.coursesMapper.toDTO(this.repository.save(coursesMapper.toEntity(entity)));
    }

    public CoursesDTO findById(@NotNull @Positive Long id) {
        return this.repository.findById(id).map(coursesMapper::toDTO)

                .orElseThrow(() -> new RecordNotFoudException(id));

    }

    public CoursesDTO update(@NotNull @Positive Long id,
            @RequestBody @Valid CoursesDTO courses) {
        return this.repository.findById(id).map(
                recordFound -> {
                    recordFound.setName(courses.name());
                    recordFound.setCategory(coursesMapper.convertCategoryValue(courses.category()));

                    return this.coursesMapper.toDTO(this.repository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoudException(id));

    }

    public void delete(@NotNull @Positive Long id) {
        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new RecordNotFoudException(id)));
    }
}
