package constants;

import lombok.Getter;
import utils.FileUtils;

@Getter
public enum ConfigurationData {
    Url(FileUtils.readFromJson("configData.json", "/url")),
    Username(FileUtils.readFromJson("configData.json", "/username")),
    Pword(FileUtils.readFromJson("configData.json", "/password")),
    Variant(FileUtils.readFromJson("configData.json", "/variant")),
    ProjectId(FileUtils.readFromJson("configData.json", "/projectId"));

    String data;

    ConfigurationData(String data) {
        this.data = data;


    }


}
