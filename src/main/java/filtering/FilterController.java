package filtering;

import dbstorage.model.Animal;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FilterController {

    // 단일 필터 검색과 출력
    public void searchAndDisplay(Abstract_Filter abstract_filter) {
        List<Animal> results = abstract_filter.search();

        if (results.isEmpty()) {
            // 검색 결과가 없는 경우
            System.out.println("검색 결과가 없습니다.");
        } else {
            // 검색 결과 있는 경우 출력
            results.forEach(System.out::println);
        }
    }

    // 다중 필터 조합 검색과 출력
    public void searchAndDisplayMultipleFilters(List<Abstract_Filter> filters) {
        if (filters.isEmpty()) {
            System.out.println("필터가 없습니다.");
            return;
        }

        // 첫 번째 필터로 초기 검색 수행
        List<Animal> results = filters.get(0).search();

        // 나머지 필터를 순차적으로 적용
        for (int i = 1; i < filters.size(); i++) {
            Abstract_Filter filter = filters.get(i);
            results = results.stream()
                    .filter(filter::filter)
                    .toList();
        }

        if (results.isEmpty()) {
            // 검색 결과가 없는 경우
            System.out.println("검색 결과가 없습니다.");
        } else {
            // 검색 결과 있는 경우 출력
            results.forEach(System.out::println);
        }
    }
}
