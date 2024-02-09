package com.curso.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.crudspring.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
