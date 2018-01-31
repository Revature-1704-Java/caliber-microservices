package com.revature.caliber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.repository.TrainerDAO;

@SpringBootApplication
public class TrainerRepositoryServiceApplication {
	
	@Autowired
	TrainerDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(TrainerRepositoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			List<SimpleTrainer> Trainers = dao.findAll();
			
			System.out.println(Trainers);
		};
	}
}
