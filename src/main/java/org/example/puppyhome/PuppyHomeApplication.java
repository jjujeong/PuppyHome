package org.example.puppyhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
//@SpringBootApplication
@SpringBootApplication(scanBasePackages = {"org.example.puppyhome"})
public class PuppyHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuppyHomeApplication.class, args);
	}

}
