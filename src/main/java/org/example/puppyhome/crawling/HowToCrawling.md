# Crawling 하는 법


1. http://localhost:8080/crawl 접속
2. **CrawlingApplication**을 실행
3. http://localhost:8080/crawl 새로고침
4. crawling_animal_data.json 파일을 확인 (크롤링된 json객체)
5. JsonFileTransformer를 실행
5. converted_animal_data.json 파일을 확인 (json배열로 바뀐 형태. 이 json 파일을 쓰면 됨)

`크롤링 실패 (Seq: 45212): Read timed out
크롤링 실패 (Seq: 45248): Read timed out`
이렇게 뜨는 것들은 보통 입양 공고를 확인할 수 없는 것이라 무시해도 됨
   