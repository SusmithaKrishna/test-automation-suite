package config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class TestDataProvider {
    private final Yaml yaml;

    public TestDataProvider() {
        yaml = new Yaml();
    }

    public String getConfigData(String key) throws FileNotFoundException {
        Map<String, String> data = loadConfigData();
        return data.get(key);
    }

    private Map<String, String> loadConfigData() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("envConfig\\ConfigProperties.yaml"));
        return yaml.load(inputStream);
    }

}
