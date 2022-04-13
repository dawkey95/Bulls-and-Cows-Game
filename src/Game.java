import java.io.File;
import java.io.IOException;

public class Game {
    private String gameWinner = null;

    public Game() {
    }

    public enum Level {
        EASY,
        MEDIUM,
        HARD;
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
            } else {
                i--;
            }
        }
    }

    public void printResult(Player player) {

        System.out.println(player.getName() + " guess: " + player.toString(player.playerGuess) + "\nResult: "
                + player.bulls + " bull(s), and " + player.cows + " cow(s).");

        System.out.println("-------------");
    }

    public void printGameWinner(Player player, int numberOfAttempts) {
        if (player.bulls == 4) {
            System.out.println(player.getName() + " won!");
            this.gameWinner = player.getName();
        }

        else if (numberOfAttempts == 7) {
            System.out.println("Draw!");
            this.gameWinner = player.getName();
        }
    }
}