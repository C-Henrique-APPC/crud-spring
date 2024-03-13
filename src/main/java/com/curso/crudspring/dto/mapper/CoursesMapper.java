package com.curso.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.curso.crudspring.dto.CoursesDTO;
import com.curso.crudspring.dto.LessonDTO;
import com.curso.crudspring.enums.Category;
import com.curso.crudspring.enums.Status;
import com.curso.crudspring.model.Courses;

@Component
public class CoursesMapper {

    public CoursesDTO toDTO(Courses courses) {
        if (courses == null) {
            return null;
        }
        List<LessonDTO> lessons = courses.getLessons().stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getNome(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CoursesDTO(courses.getId(), courses.getName(), courses.getCategory().getValue(),
                lessons);
    }

    public Courses toEntity(CoursesDTO dto) {
        Courses courses = new Courses();

        if (dto.id() != null) {
            courses.setId(dto.id());
        }
        courses.setName(dto.name());

        courses.setCategory(convertCategoryValue(dto.category()));
        courses.setStatus(Status.ATIVO);
        return courses;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria invalida: " + value);
        };
    }
}
