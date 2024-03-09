package com.curso.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.curso.crudspring.model.Courses;
import com.curso.crudspring.repository.CoursesRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CoursesRepository repository) {
		return args -> {
			repository.deleteAll();

			Courses c = new Courses();

			c.setName("Angular com Spring");
			c.setCategory("Front-end");

			repository.save(c);
		};
	}
}
