public class Easy extends Computer {

    //Override that is the computers guesses when attempting to guess the opponents code.
    @Override
    public void playerGuess(Player playerGuess, Player answer) {
        this.playerGuess = generateSecretCode();
        this.clue(this.playerGuess, secretCode);
    }
}