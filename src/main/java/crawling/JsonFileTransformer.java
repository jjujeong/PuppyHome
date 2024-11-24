package crawling;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.*;

public class JsonFileTransformer {

    public static void main(String[] args) {
        String inputFilePath = "crawling_animal_data.json";
        String outputFilePath = "converted_animal_data.json";

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}