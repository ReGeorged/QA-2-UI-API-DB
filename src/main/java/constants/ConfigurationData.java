package constants;

import lombok.Getter;
import utils.FileUtils;

@Getter
public enum ConfigurationData {
    URL(FileUtils.readFromJson("configData.json", "/url")),
    USERNAME(FileUtils.readFromJson("configData.json", "/username")),
    PWORD(FileUtils.readFromJson("configData.json", "/password")),
    VARIANT(FileUtils.readFromJson("configData.json", "/variant")),
    PROJECT_ID(FileUtils.readFromJson("configData.json", "/projectId")),
    NEW_PROJECT_NAME(FileUtils.readFromJson("configData.json", "/newProjectName"));

    String data;

    ConfigurationData(String data) {
        this.data = data;

    }


}
