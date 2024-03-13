package com.curso.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.curso.crudspring.enums.Category;
import com.curso.crudspring.model.Courses;
import com.curso.crudspring.model.Lesson;
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
			c.setCategory(Category.FRONT_END);

			Lesson l = new Lesson();
			l.setNome("Introdução");
			l.setYoutubeUrl("wacht-as");
			l.setCourses(c);
			c.getLessons().add(l);
			Lesson l1 = new Lesson();
			l1.setNome("Angular");
			l1.setYoutubeUrl("wacht-v=2");
			l1.setCourses(c);
			c.getLessons().add(l1);

			repository.save(c);
		};
	}
}
