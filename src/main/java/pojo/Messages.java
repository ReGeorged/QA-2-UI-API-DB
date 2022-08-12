package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Messages {

    public String id;
    public String threadId;

    public Messages(String id, String threadId) {
        this.id = id;
        this.threadId = threadId;
    }
}
