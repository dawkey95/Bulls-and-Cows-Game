import java.util.*;
import java.io.*;

public class Config {
    public void loadConfig() throws Exception {
        // create a reader object on the properties file
        FileReader reader = new FileReader("db.properties");

        // create properties object
        Properties p = new Properties();

        // Add a wrapper around reader object
        p.load(reader);

        // access properties data
        System.out.println(p.getProperty("numberOfAttempts"));
        System.out.println(p.getProperty("lengthOfSecretCodes"));
    }
}
