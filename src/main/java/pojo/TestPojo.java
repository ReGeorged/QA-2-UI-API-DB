package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "duration",
        "method",
        "name",
        "startTime",
        "endTime",
        "status"
})
@Data
public class TestPojo {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;


    @SuppressWarnings("unchecked")
    @JsonProperty("$")
    public void unpackTest(Map<String, Object> test) {
        this.duration = (String) test.get("duration");
        this.method = (String) test.get("method");
        this.startTime = (String) test.get("startTime");
        this.endTime = (String) test.get("endTime");
        this.status = (String) test.get("status");

    }

}
