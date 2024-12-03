package org.example.puppyhome.crawling;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CrawlingController {
    private int startNum = 45319;
    private int endNum = 45597;

    @GetMapping("/crawl")
    @Tag(name = "크롤링 API")
    @Operation(summary = "데이터 크롤링", description = "대전동물보호협회에서 json 데이터를 받아옵니다.")
    public String crawlAnimalData() {
        String outputFilePath = "crawling_animal_data.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File outputFile = new File(outputFilePath);
            if (!outputFile.exists() && !outputFile.createNewFile()) {
                System.err.println("파일 생성 실패: " + outputFilePath);
                return "파일 생성 실패";
            }

            try (FileWriter fileWriter = new FileWriter(outputFile, true)) {
                for (int i = startNum; i < endNum; i++) {
                    String url = "https://www.daejeon.go.kr/ani/AniStrayAnimalView.do?animalSeq=" + i + "&gubun=&menuSeq=3108&flag=&pageIndex=1";

                    try {
                        Connection.Response response = Jsoup.connect(url)
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                                .execute();

                        if (response.statusCode() != 200) {
                            continue;
                        }

                        Document document = response.parse();

                        Map<String, String> animalInfo = document.select(".bd_lst_row > li").stream()
                                .filter(element -> element.select("em > i").size() > 0 && element.select("div").size() > 0)
                                .collect(Collectors.toMap(
                                        element -> element.select("em > i").text(),
                                        element -> element.select("div").text()
                                ));

                        if (animalInfo.isEmpty()) {
                            continue;
                        }

                        String adoptionStatus = animalInfo.getOrDefault("입양상태", "");
                        if ("입양완료".equals(adoptionStatus) || adoptionStatus.isEmpty()) {
                            continue;
                        }

                        List<String> imageUrls = document.select(".photo_bimg_1 img").stream()
                                .map(img -> "https://www.daejeon.go.kr" + img.attr("src"))
                                .collect(Collectors.toList());

                        if (imageUrls.isEmpty()) {
                            continue;
                        }

                        animalInfo.put("imageUrls", String.join(", ", imageUrls));

                        String jsonString = objectMapper.writeValueAsString(animalInfo);
                        fileWriter.write(jsonString + "\n");
                        fileWriter.flush();

                    } catch (IOException e) {
                        System.err.println("크롤링 실패 (Seq: " + i + "): " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            return "파일 작성 중 오류 발생: " + e.getMessage();
        }
        return "크롤링 및 파일 저장 완료: " + outputFilePath;
    }
}