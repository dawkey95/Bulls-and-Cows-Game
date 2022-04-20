import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface SaveToFile {

    public default void saveFile() {

        System.out.println("Do you wish to save your results?");
        System.out.println("Type Yes or No.");
        String saveChoice = Keyboard.readInput();

        boolean valid = true;
        if (saveChoice.equalsIgnoreCase("Yes")) {
            System.out.println("Please name the file");
            String fileName = Keyboard.readInput();

            System.out.println("Saving results.");

            //This try-catch block contains code that creates, writes to and reads file for guesses made by
            //the computer on every turn.
            try {
                //Creates file AIGuessTracker
                FileWriter myWriter = new FileWriter(fileName + ".txt");

                //Creates file with a pathname to FileWriter created file.
                File f = new File(fileName + ".txt");

                //Writes data to file by appending every guess made by computer.
                BufferedWriter appendAIGuess = new BufferedWriter(myWriter);

                appendAIGuess.write("Bulls and Cows game result.");
                appendAIGuess.newLine();

                appendAIGuess.write("Your code was: " );
                appendAIGuess.newLine();

                appendAIGuess.write("Computer's code: " );
                appendAIGuess.newLine();

                appendAIGuess.write("---");
                appendAIGuess.newLine();

                //Each Rounds Results to be appended to .txt file.

                appendAIGuess.close();
                System.out.println("Successfully wrote to the file: " + f);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (saveChoice.equalsIgnoreCase("No")) {
            System.out.println("Results won't be saved. Thanks for playing.");
        }

        else {
            System.out.println("Input is invalid. Please enter a choice.");
        }
    }
}
