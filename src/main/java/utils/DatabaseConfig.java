package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    public static Properties loadEnv() {
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream(".env");
            props.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
