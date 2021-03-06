public class User extends Player {

    //Sets the player name to whatever the person enters their name to be.
    //Once the player enters their name it is stored in the usersName which is then
    //stored in the setName method to be referred to.
    public User() {
        System.out.println("Please enter your name:");
        String usersName = Keyboard.readInput();
        this.setName(usersName);
    }

    //This method allows the player to enter their guess when they try to guess the
    //opponents secretCode. It takes the entered string and parses it to an int
    //which is stored in the input array[i].
    public boolean userGuessInput(int[] input){
        String enteredInput = null;
        while(true) {

            if (fileContainingGuesses.size() > 0) {
                enteredInput = fileContainingGuesses.remove();
            } else {
                enteredInput = Keyboard.readInput();
            }

            if (checkInput(enteredInput)) {
                break;
            }
        }

        for (int i = 0; i < lengthOfSecretCode; i++) {
            input[i] = Integer.parseInt(enteredInput.charAt(i) + "");
        }
        return true;
    }

    //This overrides the setSecretCode method from the parent class "Player".
    @Override
    public void setSecretCode() {
        while(!userGuessInput(secretCode)){
            secretCode = this.playerGuess;
        }
    }

    //This overrides the playerGuess method that gives the user a clue to
    //how many cows or bulls they have with their latest guess.
    @Override
    public void playerGuess(Player playerGuess, Player answer) {
        clue(this.playerGuess, secretCode);
    }
}