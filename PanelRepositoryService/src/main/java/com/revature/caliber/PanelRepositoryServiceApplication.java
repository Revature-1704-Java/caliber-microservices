package com.revature.caliber;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.repository.PanelRepository;

@SpringBootApplication
public class PanelRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelRepositoryServiceApplication.class, args);
	}

}
