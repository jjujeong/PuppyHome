package org.example.puppyhome.crawling;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class JsonFileTransformer {

    @GetMapping("/JsonFileTrans")
    @Tag(name=" json 배열 파일로 바꾸기")
    public String JsonFileTransformer()  {
        String inputFilePath = "crawling_animal_data.json";
        String outputFilePath = "converted_animal_data.json";
        Path inputPath = Paths.get(inputFilePath);

        try {
            File outputFile = new File(outputFilePath);
            if (!outputFile.exists() && !outputFile.createNewFile()) {
                System.out.println("파일 생성 실패");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                JsonNode jsonObject = objectMapper.readTree(line);
                jsonArray.add(jsonObject);
            }
            bufferedReader.close();

            try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                fileWriter.write("[\n");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonNode jsonObject = jsonArray.get(i);
                    String compactObject = objectMapper.writeValueAsString(jsonObject);
                    fileWriter.write("  " + compactObject);
                    if (i < jsonArray.size() - 1) {
                        fileWriter.write(",\n");
                    }
                }
                fileWriter.write("\n]");
            }
            System.out.println("JSON 배열이 저장되었습니다: " + outputFilePath);
            Files.deleteIfExists(inputPath);
            System.out.println("파일이 성공적으로 삭제되었습니다.");
        } catch (IOException e) {
            System.err.println("JSON 변환 실패: " + e.getMessage());
        }
        return "파일이 성공적으로 변환되었습니다";
    }
}