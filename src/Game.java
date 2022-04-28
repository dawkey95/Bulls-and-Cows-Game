import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private String gameWinner = null;
    private String guessMode, saveResult;
    public List<String> saveToFile = new ArrayList<>();

    public Game() {
    }

    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }

    private Level gameDifficulty;

    public Game(Level gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public Level setGameDifficulty() {
        this.gameDifficulty = chooseDifficulty();
        return this.gameDifficulty;
    }

    public Level getGameDifficulty() {
        return this.gameDifficulty;
    }

    public Level chooseDifficulty() {

        boolean valid = false;
        while (!valid) {
            System.out.println("To start please choose a difficulty:");
            System.out.println("Press 1, 2 or 3 to make your choice.");
            System.out.println("\t1. Easy");
            System.out.println("\t2. Medium");
            System.out.println("\t3. Hard");

            String enteredInput = Keyboard.readInput();

            if (enteredInput.equalsIgnoreCase("1")) {
                gameDifficulty = Level.EASY;
                System.out.println("Easy Mode");
                valid = true;
            }

            else if (enteredInput.equalsIgnoreCase("2")) {
                gameDifficulty = Level.MEDIUM;
                System.out.println("Medium Mode");
                valid = true;
            }

            else if (enteredInput.equalsIgnoreCase("3")) {
                gameDifficulty = Level.HARD;
                System.out.println("Hard Mode");
                valid = true;
            }

            else {
                System.out.println("Input is invalid. Please enter a choice.");
            }
        }
        return gameDifficulty;
    }


    public void playGame(User user, Computer computer) {
        int numberOfAttempts = 7;

        for (int i = 1; i <= numberOfAttempts; i++) {
            if (gameWinner != null) {
                break;
            }

            System.out.print("Please enter your guess. Guess " + i + ": ");

            if (user.userGuessInput(user.playerGuess)) {
                user.playerGuess(user, computer);
                printResult(user);
                printGameWinner(user, i);

                if (this.gameWinner == null) {
                    computer.playerGuess(computer, user);
                    this.printResult(computer);
                    this.printGameWinner(computer, i);
                }
            }

            else {
                i--;
            }
        }
        saveResult();
        saveFile();
    }

    public void printResult(Player player) {

        System.out.println(player.getName() + " guess: " + player.toString(player.playerGuess) + "\nResult: "
                + player.bulls + " bull(s), and " + player.cows + " cow(s).");
        System.out.println("------");

        saveToFile.add(player.getName() + " guess: " + player.toString(player.playerGuess) + "\nResult: "
                + player.bulls + " bull(s), and " + player.cows + " cow(s).");
        saveToFile.add("------");

    }

    public void printGameWinner(Player player, int numberOfAttempts) {

        if (player.bulls == 4) {
            System.out.println(player.getName() + " won!");
            this.gameWinner = player.getName();
            saveToFile.add("\n" + player.getName() + " won the game! Congratulations! 😃");
        }

        else if (numberOfAttempts == 7) {
            System.out.println("Draw!");
            this.gameWinner = player.getName();
            saveToFile.add("\n The game was a draw!");
        }
    }

    public void setGuessMode() {
        boolean valid =  false;

        while(!valid) {
            System.out.println("Please choose a guess method:");
            System.out.println("\t1. Manually enter guesses");
            System.out.println("\t2. Automatically guess from a file");

            String guessInput = Keyboard.readInput();

            if(guessInput.equalsIgnoreCase("1")) {
                this.guessMode = "Manually";
                System.out.println("You have chosen to manually enter all your guesses. \nGood luck.");
                valid = true;
            }

            else if(guessInput.equalsIgnoreCase("2")) {
                this.guessMode = "Automatic";
                System.out.println("You have chosen to automatically enter guesses from a file. \nGood luck.");
                valid = true;
            }

            else{
                System.out.println("Please reenter your choice as that was an invalid response.");
            }
        }
    }

    public String getGuessmode() {
        return this.guessMode;
    }

    public void guessFromFile(Player user) {
        while(true) {
            System.out.println("Please enter a file name to be used.");

            String nameOfFile = Keyboard.readInput();
            File newFile = new File(nameOfFile);

            try(BufferedReader fileReader = new BufferedReader(new FileReader(newFile + ".txt"))) {
                String guess = null;
                while((guess = fileReader.readLine()) != null) {
                    user.fileContainingGuesses.add(guess);
                }
            }

            catch(IOException e) {
                e.printStackTrace();
                continue;
            }
            break;
        }
    }

    public void saveResult() {
        boolean valid = false;
        while (!valid) {
            System.out.println("Do you wish to save your results?");
            System.out.println("Type Yes or No.");
            String saveChoice = Keyboard.readInput();

            if (saveChoice.equalsIgnoreCase("Yes")) {
                this.saveResult = "Yes";
                valid = true;
            } else if (saveChoice.equalsIgnoreCase("No")) {
                System.out.println("Results won't be saved. Thanks for playing.");
                this.saveResult = "No";
                valid = true;
            } else {
                System.out.println("Input is invalid. Please enter a choice.");
            }
        }
    }

    public void saveFile() {
        while(this.saveResult.equals("Yes")) {
            System.out.println("Enter a file name:");
            String fileName = Keyboard.readInput();

            File newFile = new File(fileName);

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                for (int i = 0; i < saveToFile.size(); i++) {
                    writer.write(saveToFile.get(i));
                    writer.newLine();
                }
            }

            catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }
}