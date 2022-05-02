import java.util.ArrayList;

public class Hard extends Computer {
    ArrayList<int[]> AIGuesses = new ArrayList<>();

    int[] testing = new int[4];

    public Hard() {
        String intToString = "";

        //A for-loop where i = 0123 is the lowest possible guess number and i < 9876 is the largest possible guess
        // that can be made by the AI.

        for (int i = 0123; i < 9876; i++) {
            intToString = Integer.toString(i);
            try {
                for (int x = 0; x < 4; x++) {
                    int num = Integer.parseInt(intToString.charAt(x) + "");
                    for (int k = 0; k < x; k++) {
                        if (num == testing[k]) {
                            throw new DupeNumException("");
                        }
                    }
                    testing[x] = num;
                }
                AIGuesses.add(testing.clone());
            }

            catch (Exception e) {
            }
        }
    }

    @Override
    public void playerGuess(Player playerGuess, Player answer) {

        //On the AI's first try, choose a random guess from arraylist
        int randomIndex = (int) (Math.random() * AIGuesses.size());

        for (int i = 0; i < lengthOfSecretCode; i++) {
            this.playerGuess[i] = AIGuesses.get(randomIndex)[i];
        }

        this.clue(this.playerGuess, answer.secretCode);

        //New array is created to remove
        ArrayList<int[]> newGuesses = new ArrayList<>();

        int countBulls = this.bulls;
        int countCows = this.cows;

        for (int i = 0; i < AIGuesses.size(); i++) {
            this.clue(AIGuesses.get(i), this.playerGuess);
            if (countBulls != this.bulls || countCows != this.cows) {
                newGuesses.add(AIGuesses.get(i));
            }
        }

        for (int i = 0; i < newGuesses.size(); i++) {
            AIGuesses.remove(newGuesses.get(i));
        }

        this.bulls = countBulls;
        this.cows = countCows;
    }
}
