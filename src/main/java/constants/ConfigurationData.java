package constants;

import lombok.Getter;
import utils.FileUtils;

@Getter
public enum ConfigurationData {


    URL(FileUtils.readFromJson("configData.json", "/url")),
    Username(FileUtils.readFromJson("configData.json", "/username")),
    Pword(FileUtils.readFromJson("configData.json", "/password")),
    Variant(FileUtils.readFromJson("configData.json", "/variant"));

    String data;

    ConfigurationData(String data) {
        this.data = data;


    }


}
