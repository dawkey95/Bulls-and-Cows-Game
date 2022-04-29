import java.io.*;
import java.util.Properties;

public class Easy extends Computer {

    //Override that is the computers guesses when attempting to guess the opponents code.
    @Override
    public void playerGuess(Player playerGuess, Player answer) {
        this.playerGuess = generateSecretCode();
        this.clue(this.playerGuess, secretCode);
    }

//    @Override
//    public void readConfig() {
//
//        Properties prop = new Properties();
//        try(InputStream config = new FileInputStream("config.properties")) {
//            prop.load(config);
//            System.out.println("Testing" + config);
//        }
//        catch(Exception e) {
//            System.out.println("Unable to find the specified properties file");
//            e.printStackTrace();
//        }
//    }

//    public void readConfig() {
//        configFile = new Properties();
//
//        try {
//            configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
//        } catch(Exception eta){
//            eta.printStackTrace();
//        }
//    }

//    public String getProperty(String key) {
//        return this.configFile.getProperty(key);
//    }
}
