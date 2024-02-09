package org.example.fromScratch.jsonParsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.fromScratch.Json;
import org.example.fromScratch.jsonParsing.pojo.AuthorPOJO;
import org.example.fromScratch.jsonParsing.pojo.BookPOJO;
import org.example.fromScratch.jsonParsing.pojo.DatePOJO;
import org.example.fromScratch.jsonParsing.pojo.SimpleTestCaseJsonPOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {
    private String simpleTestCaseJsonSource = "{  \n" +
        " \"title\": \"Code From Scratch\", \n" +
        " \"author\": \"Run\" \n" +
        "  }";
    private String dayScenario1 = "{  \n" +
            " \"date\": \"2020-01-22\", \n" +
            " \"name\": \"Lala\" \n" +
            "  }";
    private String authorBookScenario = "{\n" +
            "  \"authorName\": \"Aha\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2019-01-01\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"publishDate\": \"2019-01-01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @org.junit.jupiter.api.Test
    void parse() throws IOException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText(), "Code From Scratch");
    }

    @Test
    void fromJson() throws IOException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        assertEquals(pojo.getTitle(), "Code From Scratch");
    }
    @Test
    void toJson(){
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        assertEquals(node.get("title").asText(), "Testing 123");
    }
    @Test
    void stringify() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.stringify(node));
    }
    @Test
    void print() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.print(node));
    }
    @Test
    void dayTestScenario1() throws IOException {
        JsonNode node = Json.parse(dayScenario1);
        DatePOJO pojo = Json.fromJson(node, DatePOJO.class);

       assertEquals("2020-01-22", pojo.getDate().toString());

    }
    @Test
    void setAuthorBookScenario() throws IOException {
        JsonNode node = Json.parse(authorBookScenario);
        AuthorPOJO pojo = Json.fromJson(node, AuthorPOJO.class);

        System.out.println("author: " +pojo.getAuthorName());
        for(BookPOJO bp : pojo.getBooks()){
            System.out.println("Book: " + bp.getTitle());
            System.out.println("Is in print: " + bp.isInPrint());
            System.out.println("date: " + bp.getPublishDate());
        }

    }
}