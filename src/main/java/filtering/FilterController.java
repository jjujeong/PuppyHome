//package filtering;
//
//import dbstorage.model.Animal;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//public class FilterController {
//
//    // 단일 필터 검색과 출력
//    public void searchAndDisplay(Abstract_Filter abstract_filter) {
//        List<Animal> results = abstract_filter.search();
//
//        if (results.isEmpty()) {
//            // 검색 결과가 없는 경우
//            System.out.println("검색 결과가 없습니다.");
//        } else {
//            // 검색 결과 있는 경우 출력
//            results.forEach(System.out::println);
//        }
//    }
//
//    // 다중 필터 조합 검색과 출력
//    public void searchAndDisplayMultipleFilters(List<Abstract_Filter> filters) {
//        if (filters.isEmpty()) {
//            System.out.println("필터가 없습니다.");
//            return;
//        }
//
//        // 첫 번째 필터로 초기 검색 수행
//        List<Animal> results = filters.get(0).search();
//
//        // 나머지 필터를 순차적으로 적용
//        for (int i = 1; i < filters.size(); i++) {
//            Abstract_Filter filter = filters.get(i);
//            results = results.stream()
//                    .filter(filter::filter)
//                    .toList();
//        }
//
//        if (results.isEmpty()) {
//            // 검색 결과가 없는 경우
//            System.out.println("검색 결과가 없습니다.");
//        } else {
//            // 검색 결과 있는 경우 출력
//            results.forEach(System.out::println);
//        }
//    }
//}


//////// 스웨거 설정 (조건 입력하는 방식) /////////
package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Animal Filter API", description = "유기동물 검색을 위한 필터 API")
public class FilterController {

    private final AnimalRepository animalRepository;

    public FilterController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Operation(summary = "단일 조건 검색", description = "하나의 필터 조건으로 유기동물을 검색합니다.")
    @GetMapping("/filter/single")
    public List<Animal> searchSingleFilter(
            @Parameter(description = "필터 유형 (예: type, gender, breed, furcolor, age, weight)") @RequestParam String filterType,
            @Parameter(description = "필터 값 (예: 개, 암컷, 푸들, 6, 6~12, 1~2.5)") @RequestParam String filterValue
    ) {
        Abstract_Filter filter;

        // 범위 처리
        if (filterValue.contains("~")) {
            String[] range = filterValue.split("~");
            double start = Double.parseDouble(range[0]);
            double end = Double.parseDouble(range[1]);

            switch (filterType.toLowerCase()) {
                case "age":
                    filter = new Filter_Age(start, end, animalRepository);
                    break;
                case "weight":
                    filter = new Filter_Weight(start, end, animalRepository);
                    break;
                default:
                    throw new IllegalArgumentException("지원되지 않는 범위 필터 유형입니다: " + filterType);
            }
        } else {
            // 단일 값 처리
            switch (filterType.toLowerCase()) {
                case "type":
                    filter = new Filter_Type(filterValue, animalRepository);
                    break;
                case "gender":
                    filter = new Filter_Gender(filterValue, animalRepository);
                    break;
                case "breed":
                    filter = new Filter_Breed(filterValue, animalRepository);
                    break;
                case "furcolor":
                    filter = new Filter_FurColor(filterValue, animalRepository);
                    break;
                case "age":
                    double age = Double.parseDouble(filterValue);
                    filter = new Filter_Age(age, animalRepository);
                    break;
                case "weight":
                    double weight = Double.parseDouble(filterValue);
                    filter = new Filter_Weight(weight, animalRepository);
                    break;
                default:
                    throw new IllegalArgumentException("지원되지 않는 필터 유형입니다: " + filterType);
            }
        }

        return filter.search();
    }


    @Operation(summary = "다중 조건 검색", description = "여러 필터 조건을 조합하여 유기동물을 검색합니다.")
    @GetMapping("/filter/multiple")
    public List<Animal> searchMultipleFilters(
            @Parameter(description = "필터 유형 목록 (예: type, gender, age, weight)") @RequestParam List<String> filterTypes,
            @Parameter(description = "필터 값 목록 (예: 개, 암컷, 6~12, 1.0~2.5)") @RequestParam List<String> filterValues
    ) {
        if (filterTypes.size() != filterValues.size()) {
            throw new IllegalArgumentException("필터 유형과 값의 개수가 일치해야 합니다.");
        }

        List<Abstract_Filter> filters = new ArrayList<>();
        for (int i = 0; i < filterTypes.size(); i++) {
            String type = filterTypes.get(i);
            String value = filterValues.get(i);

            // 범위 처리
            if (value.contains("~")) {
                String[] range = value.split("~");
                double start = Double.parseDouble(range[0]);
                double end = Double.parseDouble(range[1]);

                switch (type.toLowerCase()) {
                    case "age":
                        filters.add(new Filter_Age(start, end, animalRepository));
                        break;
                    case "weight":
                        filters.add(new Filter_Weight(start, end, animalRepository));
                        break;
                    default:
                        throw new IllegalArgumentException("지원되지 않는 범위 필터 유형입니다: " + type);
                }
            } else {
                switch (type.toLowerCase()) {
                    case "type":
                        filters.add(new Filter_Type(value, animalRepository));
                        break;
                    case "gender":
                        filters.add(new Filter_Gender(value, animalRepository));
                        break;
                    case "breed":
                        filters.add(new Filter_Breed(value, animalRepository));
                        break;
                    case "furcolor":
                        filters.add(new Filter_FurColor(value, animalRepository));
                        break;
                    case "age":
                        double age = Double.parseDouble(value);
                        filters.add(new Filter_Age(age, animalRepository));
                        break;
                    case "weight":
                        double weight = Double.parseDouble(value);
                        filters.add(new Filter_Weight(weight, animalRepository));
                        break;
                    default:
                        throw new IllegalArgumentException("지원되지 않는 필터 유형입니다: " + type);
                }
            }
        }

        List<Animal> results = filters.get(0).search();

        for (int i = 1; i < filters.size(); i++) {
            Abstract_Filter filter = filters.get(i);
            results = results.stream()
                    .filter(filter::filter)
                    .toList();
        }

        return results;
    }

}
