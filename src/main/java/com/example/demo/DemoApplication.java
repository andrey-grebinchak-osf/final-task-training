package com.example.demo;

import com.example.demo.repo.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	//check whether connection to DB is established
	@Bean
	CommandLineRunner checkConnection(TestRepository repository) {
		return args -> log.info("Amount of tests: " + repository.count());
	}

}
