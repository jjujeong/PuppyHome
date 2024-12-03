package org.example.puppyhome.crawling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrawlingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("크롤링 시작...");
        CrawlingController crawlingController = new CrawlingController();
        crawlingController.crawlAnimalData();
    }
}