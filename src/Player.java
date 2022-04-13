//Parent class of the game
public abstract class Player {

    //Creates the variables and array's needed in the Player class.
    protected int bulls, cows;
    protected int[] secretCode = new int[4];
    protected int[] playerGuess = new int[4];
    private String player;

    //Child classes will be overriding this method when extended to set each player's secret code.
    public abstract void setSecretCode();

    public abstract void playerGuess(Player playerGuess, Player answer);

    public void clue(int[] playerGuess, int[] secretCode) {
        this.bulls = 0;
        this.cows = 0;

        //Loops through the playerGuess array and the specialCode array to see if there are
        // common digits with exact matches in the array. If so it adds 1 to bull count.
        for (int i = 0; i < secretCode.length; i++) {
            if (playerGuess[i] == secretCode[i]) {
                this.bulls++;
            }
        }

        //Loops through the playerGuess array and the specialCode array to see if there are
        // common digits in wrong position of the array. If so it adds 1 to cow count.
        for (int i = 0; i < playerGuess.length; i++) {
            for (int j = 0; j < secretCode.length; j++) {
                if (i != j && playerGuess[i] == secretCode[j]) {
                    this.cows++;
                }
            }
        }
    }

    public class DupeNumException extends Exception {
        public DupeNumException(String message) {
            super(message);
        }
    }

    public boolean checkInput(String input) {
        try {
            if (input.length() != 4) {
                throw new IndexOutOfBoundsException("You need to enter 4 numbers!");
            }

            //Nested for-loops check and match each digit to ensure no duplicates. If duplicate error is thrown
            //and user is asked to enter 4 different numbers.
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i != j && input.charAt(i) == input.charAt(j)) {
                        throw new DupeNumException("You need to enter 4 different numbers!");
                    }
                }
            }
            Integer.parseInt(input);
        }

        //If user enters anything but a number an error is produced asking the user to please
        //only enter numbers.
        catch (NumberFormatException e) {
            System.out.println("You did not enter a number!");
            return false;
        }

        catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return false;
        }

        return true;
    }

    public String toString(int[] secretCode) {
        String s = "";
        for (int i = 0; i < 4; i++) {
            s += secretCode[i];
        }
        return s;
    }

    public String getName() {
        return player;
    }

    public void setName(String player) {
        this.player = player;
    }
}
