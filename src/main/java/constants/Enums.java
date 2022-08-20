package constants;


import lombok.Getter;
import utils.FileUtils;

@Getter
public enum Enums {
    EMAIL(FileUtils.readFromJson("configData.json", "/email")),
    CLIENT_ID(FileUtils.readFromJson("client_secret.json", "/installed/client_id")),
    CLIENT_SECRET(FileUtils.readFromJson("client_secret.json", "/installed/client_secret")),
    REFRESH_TOKEN(FileUtils.readFromJson("client_secret.json", "/installed/refresh_token")),
    URL(FileUtils.readFromJson("configData.json", "/url"));

    String data;

    Enums(String data) {
        this.data = data;


    }


}
