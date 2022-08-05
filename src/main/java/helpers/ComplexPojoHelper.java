package helpers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComplexPojoHelper {

    public static <T> T pojoHelper(String whatToRead, Class<T> whatClass) {
        try {
            T user = new ObjectMapper()
                    .readerFor(whatClass)
                    .readValue(whatToRead);
            return user;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

