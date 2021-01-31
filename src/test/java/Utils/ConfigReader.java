package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        String path = "configuration.properties";

        FileInputStream input = null;
        try {
            // this line will open your file
            input = new FileInputStream(path);
            properties= new Properties();
            // We need to load opened file to the properties
            properties.load(input);
            input.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key){
        // getProperty method takes one string as a key
        //and it will return value from .properties file
        return properties.getProperty(key);
    }


}
