import java.util.ArrayList;
import java.util.Collections;

public abstract class Computer extends Player {

    //Sets the player name to Computer
    public Computer() {
        this.setName("Computer");
    }

    //Method to generate secret code for the computer to use for guessing the players number
    //and generates the computers secret code that the player tries to guess.
    public int[] generateSecretCode() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        if (numbers.get(0) == 0) {
            Collections.shuffle(numbers);
        }

        int[] randomNumber = new int[4];
        for (int j = 0; j < 4; j++) {
            randomNumber[j] = numbers.get(j);
        }
        return randomNumber;
    }

    //Override that sets the methods secretCode that the opponent tries to guess
    @Override
    public void setSecretCode() {
        this.secretCode = generateSecretCode();
    }


}