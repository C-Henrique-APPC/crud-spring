package com.curso.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CoursesDTO(
        Long id,
        @NotNull @NotBlank @Length(min = 5, max = 100) String name,
        @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category

) {

}
