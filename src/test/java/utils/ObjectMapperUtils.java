package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){


        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
