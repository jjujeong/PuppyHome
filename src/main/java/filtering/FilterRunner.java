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
        System.out.println("[종류가 '개'인 동물 검색]");
        searchController.searchAndDisplay(typeSearch);

        // 성별 검색
        Abstract_Filter genderSearch = new Filter_Gender("암컷", animalRepository);
        System.out.println("\n[성별이 '암컷'인 동물 검색]");
        searchController.searchAndDisplay(genderSearch);

        // 나이  검색
        Abstract_Filter ageSearch = new Filter_Age(6, 12, animalRepository);
        System.out.println("\n[나이가 '6개월에서 12개월' 사이인 동물 검색]");
        searchController.searchAndDisplay(ageSearch);

        Abstract_Filter ageSearch2 = new Filter_Age(6, animalRepository);
        System.out.println("\n[나이가 '6개월'인 동물 검색]");
        searchController.searchAndDisplay(ageSearch2);

        // 종 검색
        Abstract_Filter breedSearch = new Filter_Breed("푸들", animalRepository);
        System.out.println("\n[종이 '푸들'인 동물 검색]");
        searchController.searchAndDisplay(breedSearch);

        // 털색 검색
        Abstract_Filter furColorSearch = new Filter_FurColor("황색", animalRepository);
        System.out.println("\n[털색이 '황색'인 동물 검색]");
        searchController.searchAndDisplay(furColorSearch);

        // 몸무게 검색
        Abstract_Filter WeihtSearch = new Filter_Weight(1.0,2.5, animalRepository);
        System.out.println("\n[몸무게가 '1kg에서 2.5kg' 사이인 동물 검색]");
        searchController.searchAndDisplay(WeihtSearch);

        Abstract_Filter WeihtSearch2 = new Filter_Weight(1.5, animalRepository);
        System.out.println("\n[몸무게가 '1.5kg'인 동물 검색]");
        searchController.searchAndDisplay(WeihtSearch2);
    }
}
