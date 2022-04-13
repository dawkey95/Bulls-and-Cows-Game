import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Medium extends Computer {

    //Override that is the computers guesses when attempting to guess the opponents code.
    @Override
    public void playerGuess(Player playerGuess, Player answer) {
        this.playerGuess = generateSecretCode();
        this.clue(this.playerGuess, answer.secretCode);

        //This try-catch block contains code that creates, writes to and reads file for guesses made by
        //the computer on every turn.
        try {
            //Creates file AIGuessTracker
            FileWriter myWriter = new FileWriter("AIGuessTracker.txt", true);

            //Creates file with a pathname to FileWriter created file.
            File f = new File("AIGuessTracker.txt");

            //Writes data to file by appending every guess made by computer.
            BufferedWriter appendAIGuess = new BufferedWriter(myWriter);
            appendAIGuess.write(Arrays.toString(this.playerGuess));
            appendAIGuess.newLine();
            appendAIGuess.close();
            System.out.println("Successfully wrote to the file: " + f);

            //Scanner to read data stored in file
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
