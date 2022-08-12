package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Mail {

    public String id;
    public String threadId;
    public String nextPageToken;
    public int resultSizeEstimate;

    @SuppressWarnings("unchecked")
    @JsonProperty("$")
    public void unpackMail(Map<String, Object> mail) {
        this.nextPageToken = (String) mail.get("nextPageToken");
        this.resultSizeEstimate = (int) mail.get("resultSizeEstimate");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("messages")
    public void unpackMessages(Map<String, Object> mail) {
        this.id = (String) mail.get("id");
        this.threadId = (String) mail.get("threadId");
    }
}
