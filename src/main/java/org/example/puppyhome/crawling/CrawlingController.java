package org.example.puppyhome.crawling;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
public class CrawlingController {

    private static final Logger logger = LoggerFactory.getLogger(CrawlingController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/crawl")
    @Tag(name = "크롤링 API")
    @Operation(summary = "데이터 크롤링", description = "대전동물보호협회에서 json 데이터를 받아옵니다.")
    public String crawlAnimalData(
            @RequestParam(required = true) int startNum,
            @RequestParam(required = true) int endNum
    ) {
        String outputFilePath = "crawling_animal_data.json";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            int processors = Runtime.getRuntime().availableProcessors();
            ExecutorService executorService = Executors.newFixedThreadPool(processors);

            File outputFile = new File(outputFilePath);
            if (!outputFile.exists() && !outputFile.createNewFile()) {
                System.err.println("파일 생성 실패: " + outputFilePath);
                return "파일 생성 실패";
            }

            for (int i = startNum; i < endNum + 1; i++) {
                final int animalSeq = i;
                executorService.submit(() -> crawlSingleAnimal(animalSeq, writer));
            }

            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);

        } catch (IOException | InterruptedException e) {
            logger.error("크롤링 중 오류 발생", e);
            return "크롤링 실패: " + e.getMessage();
        }

        return "크롤링 및 파일 저장 완료: " + outputFilePath;
    }

    private void crawlSingleAnimal(int animalSeq, BufferedWriter writer) {
        String url = "https://www.daejeon.go.kr/ani/AniStrayAnimalView.do?animalSeq=" + animalSeq + "&gubun=&menuSeq=3108&flag=&pageIndex=1";

        try {
            Connection.Response response = Jsoup.connect(url)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .execute();

            if (response.statusCode() != 200) {
                return;
            }

            Document document = response.parse();

            Map<String, String> animalInfo = document.select(".bd_lst_row > li").stream()
                    .filter(element -> element.select("em > i").size() > 0 && element.select("div").size() > 0)
                    .collect(Collectors.toMap(
                            element -> element.select("em > i").text(),
                            element -> element.select("div").text()
                    ));

            String adoptionStatus = animalInfo.getOrDefault("입양상태", "");
            if (animalInfo.isEmpty() || "입양완료".equals(adoptionStatus) || "입양예정".equals(adoptionStatus)) {
                return;
            }

            List<String> imageUrls = document.select(".photo_bimg_1 img").stream()
                    .map(img -> "https://www.daejeon.go.kr" + img.attr("src"))
                    .collect(Collectors.toList());

            if (imageUrls.isEmpty()) {
                return;
            }

            animalInfo.put("imageUrls", String.join(", ", imageUrls));

            String jsonString = objectMapper.writeValueAsString(animalInfo);
            synchronized (writer) {
                writer.write(jsonString);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            logger.error("크롤링 실패 (Seq: {}): {}", animalSeq, e.getMessage());
        }
    }
}