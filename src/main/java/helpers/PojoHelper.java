package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.Test;
import pojo.Mail;
import pojo.Messages;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PojoHelper {

    public static <T> T jsonPojoHelper(String whatToRead, Class<T> whatClass) {
        try {
            T pojo = new ObjectMapper()
                    .readerFor(whatClass)
                    .readValue(whatToRead);
            return pojo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public static List veryBadWayToDeserialize(String whatJson) {
        JsonElement fileElement = JsonParser.parseString(whatJson);
        JsonObject fileObject = fileElement.getAsJsonObject();
//          THESE ARE NOT NEEDED
//        String nextPageToken = fileObject.get("nextPageToken").getAsString();
//        Integer resultSizeEstimate = fileObject.get("resultSizeEstimate").getAsInt();

        JsonArray messagesArray = fileObject.get("messages").getAsJsonArray();
        List<Messages> messagesList = new ArrayList<>();
        for (JsonElement msgElement : messagesArray) {
            JsonObject msgJsonObject = msgElement.getAsJsonObject();
            String id = msgJsonObject.get("id").getAsString();
            String threadId = msgJsonObject.get("threadId").getAsString();
            Messages messages = new Messages(id, threadId);
            messagesList.add(messages);


        }
        return messagesList;
    }


}
