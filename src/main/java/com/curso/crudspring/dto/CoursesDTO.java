package com.curso.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CoursesDTO(
        Long id,
        @NotNull @NotBlank @Length(min = 5, max = 100) String name,
        @NotNull String category

) {

}
