import java.util.ArrayList;

public class Medium extends Computer {

    ArrayList<int[]> computerGuesses = new ArrayList<>();

    //Override that is the computers guesses when attempting to guess the opponents code.
    @Override
    public void playerGuess(Player playerGuess, Player answer) {
        this.playerGuess = generateSecretCode();
        this.clue(this.playerGuess, answer.secretCode);

        int[] guessTracker = generateSecretCode();

        if(!computerGuesses.contains(guessTracker)) {
            computerGuesses.add(guessTracker);
            this.playerGuess = guessTracker;
        }
        else {
            this.playerGuess(playerGuess, answer);
        }
    }
}
