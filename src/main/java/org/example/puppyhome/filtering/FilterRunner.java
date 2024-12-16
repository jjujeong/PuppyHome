//package filtering;
//
//import dbstorage.repository.AnimalRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class FilterRunner implements CommandLineRunner {
//
//    private final FilterController filterController;
//    private final AnimalRepository animalRepository;
//
//    public FilterRunner(FilterController filterController, AnimalRepository animalRepository) {
//        this.filterController = filterController;
//        this.animalRepository = animalRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // 단일 조건 검색
//        System.out.println("[종류가 '개'인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Type("개", animalRepository)
//        );
//
//        System.out.println("[성별이 '암컷'인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Gender("암컷", animalRepository)
//        );
//
//        System.out.println("[나이가 '6개월에서 12개월' 사이인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Age(6, 12, animalRepository)
//        );
//
//        System.out.println("[나이가 '6개월' 인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Age(6,  animalRepository)
//        );
//
//        System.out.println("[몸무게가 '1kg에서 2.5kg' 사이인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Weight(1.0, 2.5, animalRepository)
//        );
//
//        System.out.println("[몸무게가 '3.7kg' 인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Weight(3.7, animalRepository)
//        );
//
//        System.out.println("[털색이 '황색' 인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_FurColor("황색", animalRepository)
//        );
//
//        System.out.println("[종이 '푸들' 인 동물 검색 결과]");
//        filterController.searchAndDisplay(new Filter_Breed("푸들", animalRepository)
//        );
//
//        // 다중 조건 검색
//        System.out.println("[종류가 '고양이', 성별이 '수컷', 몸무게가 '1kg에서 3kg' 사이인 동물 검색 결과]");
//        filterController.searchAndDisplayMultipleFilters(
//                List.of(
//                        new Filter_Type("고양이", animalRepository),
//                        new Filter_Gender("수컷", animalRepository),
//                        new Filter_Weight(1, 3, animalRepository)
//                )
//        );
//    }
//}
//

//////// 스웨거 설정 (조건 입력하는 방식) /////////
package org.example.puppyhome.filtering;

import org.example.puppyhome.dbstorage.model.Animal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterRunner implements CommandLineRunner {

    private final FilterController filterController;

    public FilterRunner(FilterController filterController) {
        this.filterController = filterController;
    }

    @Override
    public void run(String... args) throws Exception {

        // 단일 조건 검색
        System.out.println("[종류가 '개'인 동물 검색 결과]");
        List<Animal> typeResults = filterController.searchSingleFilter("type", "개");
        typeResults.forEach(System.out::println);

        System.out.println("\n[성별이 '암컷'인 동물 검색 결과]");
        List<Animal> genderResults = filterController.searchSingleFilter("gender", "암컷");
        genderResults.forEach(System.out::println);

        System.out.println("\n[나이가 '6개월' 인 동물 검색 결과]");
        List<Animal> ageResults = filterController.searchSingleFilter("age", "6");
        ageResults.forEach(System.out::println);


        System.out.println("\n[나이가 '6개월에서 12개월' 사이인 동물 검색 결과]");
        List<Animal> ageRangeResults = filterController.searchSingleFilter("age", "6~12");
        ageRangeResults.forEach(System.out::println);

        System.out.println("\n[털색이 '황색' 인 동물 검색 결과]");
        List<Animal> furColorResults = filterController.searchSingleFilter("furColor", "황색");
        furColorResults.forEach(System.out::println);

        System.out.println("\n[몸무게가 '1kg에서 2.5kg' 사이인 동물 검색 결과]");
        List<Animal> weightRangeResults = filterController.searchSingleFilter("weight", "1~2.5");
        weightRangeResults.forEach(System.out::println);

        System.out.println("\n[종이 '푸들' 인 동물 검색 결과]");
        List<Animal> breedtRangeResults = filterController.searchSingleFilter("breed", "푸들");
        breedtRangeResults.forEach(System.out::println);


        // 다중 조건 검색
        System.out.println("\n[종류가 '고양이', 성별이 '수컷', 몸무게가 '1kg에서 3kg' 사이인 동물 검색 결과]");
        List<Animal> multipleFiltersResults = filterController.searchMultipleFilters(
                List.of("type", "gender", "weight"),
                List.of("고양이", "수컷", "1~3")
        );
        multipleFiltersResults.forEach(System.out::println);
    }

}
