public class BullsAndCows {

    //This method contains welcome messages and some information about the game.
    private static void printWelcomeMessage() {
        System.out.println("Welcome to BULLS and COWS.");
        System.out.println("The objective is for you to guess a 4-digit number generated by the computer.");
        System.out.println("You can also choose the difficulty for the computer.");
        System.out.println("BULLS = # common digits with exact matches.");
        System.out.println("COWS = # common digits in wrong position.");
        System.out.println("You get a set amount of attempts to guess the computers number.");
        System.out.println("GOOD LUCK AND HAVE FUN.");
        System.out.println("----------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        printWelcomeMessage();

        Computer computer = null;

        Game game = new Game();
        User user = new User();

        game.setGameDifficulty();

        switch(game.getGameDifficulty()){
            case EASY:
                computer = new Easy();
                break;

            case MEDIUM:
                computer = new Medium();
                break;

            case HARD:
                computer = new Hard();
                break;

            default:
                break;
        }

        System.out.println("Please enter your secret code:");
        user.setSecretCode();
        System.out.println("Your secret code is: " + user.toString(user.secretCode));
        game.saveToFile.add("Thanks for playing the game. \nHere are your results.");
        game.saveToFile.add("\nYour code was: " + user.toString(user.secretCode));

        computer.setSecretCode();
        game.saveToFile.add("AI code was: " + computer.toString(computer.secretCode));
        game.saveToFile.add("------");

        game.setGuessMode();
        if(game.getGuessMode().equals("Automatic")) {
            game.guessFromFile(user);
        }

        game.playGame(user, computer);
    }
}
