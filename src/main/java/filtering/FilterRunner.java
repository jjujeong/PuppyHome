package filtering;

import dbstorage.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterRunner implements CommandLineRunner {

    private final FilterController filterController;
    private final AnimalRepository animalRepository;

    public FilterRunner(FilterController filterController, AnimalRepository animalRepository) {
        this.filterController = filterController;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 단일 조건 검색
        System.out.println("[종류가 '개'인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Type("개", animalRepository)
        );

        System.out.println("[성별이 '암컷'인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Gender("암컷", animalRepository)
        );

        System.out.println("[나이가 '6개월에서 12개월' 사이인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Age(6, 12, animalRepository)
        );

        System.out.println("[나이가 '6개월' 인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Age(6,  animalRepository)
        );

        System.out.println("[몸무게가 '1kg에서 2.5kg' 사이인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Weight(1.0, 2.5, animalRepository)
        );

        System.out.println("[몸무게가 '3.7kg' 인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Weight(3.7, animalRepository)
        );

        System.out.println("[털색이 '황색' 인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_FurColor("황색", animalRepository)
        );

        System.out.println("[종이 '푸들' 인 동물 검색 결과]");
        filterController.searchAndDisplay(new Filter_Breed("푸들", animalRepository)
        );

        // 다중 조건 검색
        System.out.println("[종류가 '고양이', 성별이 '수컷', 몸무게가 '1kg에서 3kg' 사이인 동물 검색 결과]");
        filterController.searchAndDisplayMultipleFilters(
                List.of(
                        new Filter_Type("고양이", animalRepository),
                        new Filter_Gender("수컷", animalRepository),
                        new Filter_Weight(1, 3, animalRepository)
                )
        );
    }
}

