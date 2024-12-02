package filtering;

import dbstorage.model.Animal;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FilterController {

    public void searchAndDisplay(Abstract_Filter searchTemplate) {
        List<Animal> results = searchTemplate.search();

        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.println("검색 결과:");
            results.forEach(System.out::println);
        }
    }
}