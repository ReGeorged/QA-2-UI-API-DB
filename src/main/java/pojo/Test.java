package pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Test {
    @Id
    private int id;
    private String name;
    private String method_name;
    private int project_id;
    private int session_id;
    private String env;
}
