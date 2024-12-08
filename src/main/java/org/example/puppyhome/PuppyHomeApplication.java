package org.example.puppyhome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.example.puppyhome.crawling.CrawlingController;

@EnableMongoRepositories
@SpringBootApplication
public class PuppyHomeApplication implements CommandLineRunner {

	@Autowired
	private CrawlingController crawlingController;

	public static void main(String[] args) {
		SpringApplication.run(PuppyHomeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("크롤링 시작...");

		String result = crawlingController.crawlAnimalData();
		System.out.println(result);
	}
}