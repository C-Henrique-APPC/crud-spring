package com.curso.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.curso.crudspring.dto.CoursesDTO;
import com.curso.crudspring.model.Courses;

@Component
public class CoursesMapper {

    public CoursesDTO toDTO(Courses courses) {
        if (courses == null) {
            return null;
        }
        return new CoursesDTO(courses.getId(), courses.getName(), courses.getCategory());
    }

    public Courses toEntity(CoursesDTO dto) {
        Courses courses = new Courses();

        if (dto.id() != null) {
            courses.setId(dto.id());
        }
        courses.setName(dto.name());
        courses.setCategory(dto.category());
        courses.setStatus("Ativo");
        return courses;
    }
}
