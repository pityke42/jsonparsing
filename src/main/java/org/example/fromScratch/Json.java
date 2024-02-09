package org.example.fromScratch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

//process the parsings
public class Json {
    private static ObjectMapper objMapper = getDefaultObjMapper();
    private static ObjectMapper getDefaultObjMapper(){
        ObjectMapper defaultObjMapper = new ObjectMapper();
        defaultObjMapper.registerModule(new JavaTimeModule());
        defaultObjMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return  defaultObjMapper;
    }
    //parsing a json string into a json node
    public static JsonNode parse(String src) throws IOException {
        return objMapper.readTree(src);
    }
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objMapper.treeToValue(node, clazz);
    }
    public static  JsonNode toJson(Object a){
        return objMapper.valueToTree(a);
    }
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);

    }
    public static String print(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);

    }
    private static  String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objMapper.writer();
        if(pretty) objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return  objectWriter.writeValueAsString(node);



    }

}
