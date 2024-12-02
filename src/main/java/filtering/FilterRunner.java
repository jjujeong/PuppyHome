package filtering;

import dbstorage.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FilterRunner implements CommandLineRunner {

    private final FilterController searchController;
    private final AnimalRepository animalRepository;

    public FilterRunner(FilterController searchController, AnimalRepository animalRepository) {
        this.searchController = searchController;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 동물 종류 검색
        Abstract_Filter typeSearch = new Filter_Type("개", animalRepository);
        System.out.println("종류가 '개'인 동물 검색:");
        searchController.searchAndDisplay(typeSearch);

        // 성별 검색
        Abstract_Filter genderSearch = new Filter_Gender("암컷", animalRepository);
        System.out.println("\n성별이 '암컷'인 동물 검색:");
        searchController.searchAndDisplay(genderSearch);

        // 나이 범위 검색
        Abstract_Filter ageSearch = new Filter_Age(6, 12, animalRepository); // 6개월 ~ 1년
        System.out.println("\n나이가 '6개월에서 12개월' 사이인 동물 검색:");
        searchController.searchAndDisplay(ageSearch);

        Abstract_Filter ageSearch2 = new Filter_Age(6, animalRepository); // 6개월 ~ 1년
        System.out.println("\n나이가 '6개월'인 동물 검색:");
        searchController.searchAndDisplay(ageSearch2);

        // 종 검색
        Abstract_Filter breedSearch = new Filter_Breed("푸들", animalRepository); // 6개월 ~ 1년
        System.out.println("\n종이 '푸들'인 동물 검색:");
        searchController.searchAndDisplay(breedSearch);
    }
}
