package dbstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dbstorage"})
public class DbStorageApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DbStorageApplication.class);
        app.setAdditionalProfiles("dbstorage"); // "dbstorage" 프로파일 활성화
        app.run(args);
    }
}
