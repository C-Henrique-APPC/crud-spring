package com.curso.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.curso.crudspring.enums.Category;
import com.curso.crudspring.enums.Status;
import com.curso.crudspring.enums.converters.CategoryConverter;
import com.curso.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Courses SET status = 'Inativo' WHERE id = ? ")
@SQLRestriction("status = 'Ativo'")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;
}
