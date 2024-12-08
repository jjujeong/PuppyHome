package org.example.puppyhome;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = {"org.example.puppyhome"})
public class PuppyHomeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PuppyHomeApplication.class);
		app.setAdditionalProfiles("dbstorage");
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("프로그램 시작...");
	}
}