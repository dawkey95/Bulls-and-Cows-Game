import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public interface SaveToFile {

    public String writeToFile();

//    //This try-catch block contains code that creates, writes to and reads file for guesses made by
//    //the computer on every turn.
//        try {
//        //Creates file AIGuessTracker
//        FileWriter myWriter = new FileWriter("AIGuessTracker.txt", true);
//
//        //Creates file with a pathname to FileWriter created file.
//        File f = new File("AIGuessTracker.txt");
//
//        //Writes data to file by appending every guess made by computer.
//        BufferedWriter appendAIGuess = new BufferedWriter(myWriter);
//        appendAIGuess.write(Arrays.toString(this.playerGuess));
//        appendAIGuess.newLine();
//        appendAIGuess.close();
//        System.out.println("Successfully wrote to the file: " + f);
//
//        //Scanner to read data stored in file and check for duplicate guesses
//        Scanner myReader = new Scanner(f);
//        while (myReader.hasNextLine()) {
//
//            //Reads line from file and replaces expressions with "".
//            String data = myReader.nextLine()
//                    .replaceAll(",", "")
//                    .replaceAll("\\[", "")
//                    .replaceAll("\\]", "")
//                    .replaceAll(" ", "");
//
//            char dupeData[] = data.toCharArray();
//            int count;
//
//            for(int i = 0; i < dupeData.length; i++) {
//                count = 1;
//                for(int k = i + 1; k < dupeData.length; k++) {
//                    if(dupeData[i] == dupeData[k] && dupeData[i] != ' ') {
//                        count++;
//                        dupeData[k] = '0';
//                    }
//                }
//
//                //A character is considered as duplicate if count is greater than 1
//                if(count > 1 && dupeData[i] != '0')
//                    System.out.println("Testing for dupes: " + dupeData[i]);
//                generateSecretCode();
//            }
//
//            System.out.println(data);
//        }
//        myReader.close();
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

}
