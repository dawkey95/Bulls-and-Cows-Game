import java.util.*;
import java.io.*;

public class Config {
    // create properties object
    Properties p = new Properties();

    public void loadConfig() {

        // create a reader object on the properties file
        FileReader reader = null;
        try {
            reader = new FileReader("config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Add a wrapper around reader object
        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.p.getProperty(key);
    }
}
