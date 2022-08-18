package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Mail {
    public List<Messages> messages;
    public String nextPageToken;
    public int resultSizeEstimate;

    @SuppressWarnings("unchecked")
    @JsonProperty("$")
    public void unpackMail(Map<String, Object> mail) {
        this.nextPageToken = (String) mail.get("nextPageToken");
        this.resultSizeEstimate = (int) mail.get("resultSizeEstimate");
        this.messages = (List<Messages>) mail.get("messages");
    }
}

