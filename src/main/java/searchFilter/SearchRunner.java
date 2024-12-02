package searchFilter;

import dbstorage.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SearchRunner implements CommandLineRunner {

    @Autowired
    private SearchService searchService;

    @Override
    public void run(String... args) {
        // 필터 생성
        SearchFilter typeFilter = new TypeFilter("개");
        SearchFilter genderFilter = new GenderFilter("수컷");
        SearchFilter ageFilter = new AgeFilter(1 , 30); // 6개월 ~ 24개월

        // 필터 조합
        List<SearchFilter> filters = Arrays.asList(typeFilter, genderFilter, ageFilter);

        // 검색 실행 및 결과 출력
        List<Animal> results = searchService.search(filters);
        searchService.displayResults(results);
    }
}
