package filtering;

import dbstorage.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SearchRunner implements CommandLineRunner {

    private final AnimalSearchController searchController;
    private final AnimalRepository animalRepository;

    public SearchRunner(AnimalSearchController searchController, AnimalRepository animalRepository) {
        this.searchController = searchController;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 동물 종류 검색
        AnimalSearchTemplate typeSearch = new TypeSearch("개", animalRepository);
        System.out.println("종류가 '개'인 동물 검색:");
        searchController.searchAndDisplay(typeSearch);

        // 성별 검색
        AnimalSearchTemplate genderSearch = new GenderSearch("암컷", animalRepository);
        System.out.println("\n성별이 '암컷'인 동물 검색:");
        searchController.searchAndDisplay(genderSearch);

        // 나이 범위 검색
        AnimalSearchTemplate ageSearch = new AgeRangeSearch(6, 12, animalRepository); // 6개월 ~ 1년
        System.out.println("\n나이가 '6개월에서 12개월' 사이인 동물 검색:");
        searchController.searchAndDisplay(ageSearch);

        AnimalSearchTemplate ageSearch2 = new AgeRangeSearch(6, animalRepository); // 6개월 ~ 1년
        System.out.println("\n나이가 '6개월'인 동물 검색:");
        searchController.searchAndDisplay(ageSearch2);

        // 종 검색
        AnimalSearchTemplate breedSearch = new BreedSearch("푸들", animalRepository); // 6개월 ~ 1년
        System.out.println("\n종이 '푸들'인 동물 검색:");
        searchController.searchAndDisplay(breedSearch);
    }
}
