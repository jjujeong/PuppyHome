package filtering;

import dbstorage.model.Animal;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FilterController {

    public void searchAndDisplay(Abstract_Filter searchTemplate) {
        List<Animal> results = searchTemplate.search();

        if (results.isEmpty()) {
            // 검색 결과가 없는 경우
            System.out.println("검색 결과가 없습니다.");
        } else {
            // 검색 결과 있는 경우 출력
            results.forEach(System.out::println);
        }
    }
}