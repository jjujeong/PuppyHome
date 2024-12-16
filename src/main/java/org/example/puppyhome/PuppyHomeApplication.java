package org.example.puppyhome;

import org.example.puppyhome.crawling.CrawlingController;
import org.example.puppyhome.crawling.JsonFileTransformer;
import org.example.puppyhome.dbstorage.config.DbSeedConfig;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Scanner;

@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = {"org.example.puppyhome"})
@Profile("dbstorage")
public class PuppyHomeApplication implements CommandLineRunner {

	private final CrawlingController crawlingController;
	private final JsonFileTransformer jsonFileTransformer;
	private final ApplicationContext applicationContext; // ApplicationContext 추가

	public PuppyHomeApplication(CrawlingController crawlingController,
								JsonFileTransformer jsonFileTransformer,
								ApplicationContext applicationContext) { // 생성자에서 주입
		this.crawlingController = crawlingController;
		this.jsonFileTransformer = jsonFileTransformer;
		this.applicationContext = applicationContext;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PuppyHomeApplication.class);
		app.setAdditionalProfiles("dbstorage");
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.println("크롤링을 시작합니다. 시작 번호와 끝 번호를 입력하세요.");
//		System.out.print("시작 번호: ");
//		int startNum = scanner.nextInt();
//		System.out.print("끝 번호: ");
//		int endNum = scanner.nextInt();
//
//		// 1단계: 데이터 크롤링
//		System.out.println("크롤링 중...");
//		String crawlResult = crawlingController.crawlAnimalData(startNum, endNum);
//		System.out.println(crawlResult);

		// 2단계: JSON 파일 변환
		System.out.println("JSON 파일을 변환 중...");
		String transformResult = jsonFileTransformer.JsonFileTransformer();
		System.out.println(transformResult);

		// 3단계: DB 저장
		System.out.println("MongoDB에 데이터 저장을 시작합니다...");
		DbSeedConfig dbSeedConfig = applicationContext.getBean(DbSeedConfig.class); // ApplicationContext 사용
		AnimalRepository animalRepository = applicationContext.getBean(AnimalRepository.class);
		dbSeedConfig.seedMongoData(animalRepository).run();
		System.out.println("저장완료");


	}
}