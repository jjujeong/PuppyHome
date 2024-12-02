package filtering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"filtering", "dbstorage"})
public class FilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }
}