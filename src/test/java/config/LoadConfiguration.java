package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfiguration {
    public static Properties properties=new Properties();

    public static String getProperty(String key)
    {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}