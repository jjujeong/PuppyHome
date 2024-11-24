package crawling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CrawlingController {

    @GetMapping("/crawl")
    public String crawlAnimalData() {
        String outputFilePath = "crawling_animal_data.json";
        ObjectMapper objectMapper = new ObjectMapper();
        // 테스트를 위해 임의로 번호를 정함
        int startSeq = 45528;
        int endSeq = 45549;

        try (FileWriter fileWriter = new FileWriter(outputFilePath, true)) {
            for (int i = startSeq; i < endSeq; i++) {
                String url = "https://www.daejeon.go.kr/ani/AniStrayAnimalView.do?animalSeq=" + i
                        + "&gubun=&menuSeq=3108&flag=0&pageIndex=1&searchCondition3=2";

                try {
                    Connection.Response response = Jsoup.connect(url)
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                            .execute();

                    if (response.statusCode() != 200) {
                        System.out.println("URL 없음 (Seq: " + i + "): " + response.statusCode());
                        continue;
                    }

                    Document document = response.parse();

                    Map<String, String> animalInfo = document.select(".bd_lst_row > li").stream()
                            .collect(Collectors.toMap(
                                    element -> element.select("em > i").text(),
                                    element -> element.select("div").text()
                            ));

                    String adoptionStatus = animalInfo.get("입양상태");
                    if (adoptionStatus == null || !adoptionStatus.equals("입양가능")) {
                        continue;
                    }

                    List<String> imageUrls = document.select(".photo_bimg_1 img").stream()
                            .map(img -> "https://www.daejeon.go.kr" + img.attr("src"))
                            .collect(Collectors.toList());

                    animalInfo.put("imageUrls", String.join(", ", imageUrls));

                    if (animalInfo.isEmpty() && imageUrls.isEmpty()) {
                        System.out.println("빈 데이터:  " + i);
                        continue;
                    }

                    String jsonString = objectMapper.writeValueAsString(animalInfo);
                    fileWriter.write(jsonString + "\n");
                    fileWriter.flush();

                } catch (IOException e) {
                    System.err.println("크롤링 실패 (Seq: " + i + "): " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "파일 작성 중 오류 발생: " + e.getMessage();
        }
        return "크롤링 및 파일 저장 완료: " + outputFilePath;
    }
}