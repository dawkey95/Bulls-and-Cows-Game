import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    //initialised variables to be used throughout
    private String gameWinner = null;
    private String guessMode, saveResult;
    int numberOfAttempts;
    public List<String> saveToFile = new ArrayList<>();
    Config cfg = new Config();

//     Game constructor calls loadConfig() and updates the numberOfAttempts value from the config.properties
//     file with what the user set there.
    public Game() {
        cfg.loadConfig();
        numberOfAttempts = Integer.parseInt(cfg.getProperty("numberOfAttempts"));
    }

    //Next block of code sets and gets the game difficulty using enum and switches.
    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }

    private Level gameDifficulty;

    public Game(Level gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public void setGameDifficulty() {
        this.gameDifficulty = chooseDifficulty();
    }

    public Level getGameDifficulty() {
        return this.gameDifficulty;
    }

    //The user is asked to choose from predefined input requests as to which difficulty they want.
    //This input is then taken as string and using the switch in BullsAndCows.java to switch
    //between the 3 given difficulties.
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

            //If the user enters anything other than asked for they are met with an error and asked to try again.
            else {
                System.out.println("Input is invalid. Please enter a choice.");
            }
        }
        //gameDifficulty is updated accordingly and returned which is used by the getGameDifficulty method
        return gameDifficulty;
    }

    //Most used method in the game which asks the user to enter their guess or gets a generated guess form the Computer
    //This method is responsible for the round by round guesses
    public void playGame(User user, Computer computer) {
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

    //Method prints the results of each given round showing how many cows or bulls each player got
    //as well as what each player guessed.
    //This method also saves the round by round prints to a file which can be saved at the end of the game.
    public void printResult(Player player) {

        System.out.println(player.getName() + " guess: " + Player.toString(player.playerGuess) + "\nResult: "
                + player.bulls + " bull(s), and " + player.cows + " cow(s).");
        System.out.println("------");

        saveToFile.add(player.getName() + " guess: " + Player.toString(player.playerGuess) + "\nResult: "
                + player.bulls + " bull(s), and " + player.cows + " cow(s).");
        saveToFile.add("------");

    }

    //When the game is concluded the following method prints the result of the game
    //and saves the results of the game to a file if the user chooses to do so.
    public void printGameWinner(Player player, int numberOfAttempts) {

        //Winner is determined to the first player to get 4 bulls
        //Also saves the result to file with the saveToFile.add
        if (player.bulls == 4) {
            System.out.println(player.getName() + " won!");
            this.gameWinner = player.getName();
            saveToFile.add("\n" + player.getName() + " won the game! Congratulations! 😃");
        }

        //Else if no one gets 4 bulls before guess attempts are finished, the game ends in a draw.
        //Also saves the result to file with the saveToFile.add
        else if (numberOfAttempts == Integer.parseInt(cfg.getProperty("numberOfAttempts"))) {
            System.out.println("Draw!");
            this.gameWinner = player.getName();
            saveToFile.add("\n The game was a draw!");
        }
    }

    //This method sets the guess mode which the user can use.
    public void setGuessMode() {
        boolean valid =  false;

        //User is prompted to input a value of 1 or 2 to set the guess mode used.
        while(!valid) {
            System.out.println("Please choose a guess method:");
            System.out.println("\t1. Manually enter guesses");
            System.out.println("\t2. Automatically guess from a file");

            String guessInput = Keyboard.readInput();

            //If player chooses "1" then the user will manually enter all their guesses
            if(guessInput.equalsIgnoreCase("1")) {
                this.guessMode = "Manually";
                System.out.println("You have chosen to manually enter all your guesses. \nGood luck.");
                valid = true;
            }

            //If the user chooses "2" then they will be able to load guesses from a file
            else if(guessInput.equalsIgnoreCase("2")) {
                this.guessMode = "Automatic";
                System.out.println("You have chosen to automatically enter guesses from a file. \nGood luck.");
                valid = true;
            }

            //Lets user know to please enter a valid choice and prompts them to try again.
            else{
                System.out.println("Please reenter your choice as that was an invalid response.");
            }
        }
    }

    public String getGuessMode() {
        return this.guessMode;
    }

    //If the user chose "2" for auto guess form file then this method is called.
    //The user is prompted to enter the file name containing their guesses.
    public void guessFromFile(Player user) {
        while(true) {
            System.out.println("Please enter a file name to be used.");

            String nameOfFile = Keyboard.readInput();
            File newFile = new File(nameOfFile);

            //The file is tried to be read as the reader reads each line and storing it in guess.
            //While the guess is not null the data is stored in guessNoSpaces which removes all whitespace between
            //the characters if any is present.
            try(BufferedReader fileReader = new BufferedReader(new FileReader(newFile + ".txt"))) {
                String guess = null;
                String guessNoSpaces = null;
                while((guess = fileReader.readLine()) != null) {
                    guessNoSpaces = guess.replaceAll("\\s+","");
                    user.fileContainingGuesses.add(guessNoSpaces);
                }
            }

            catch(IOException e) {
                e.printStackTrace();
                continue;
            }
            break;
        }
    }

    //This method is called at the end of the game asking the player if they wish to save their game results to a file.
    //They are asked to choose with yes or no, the program will ignore case due to .equalsIgnoreCase()
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

    //If the user chose to save file then this method is called which asks the user to name their file
    //The file is then created and data written to file using the saveToFile which is utilised through the Game.java
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