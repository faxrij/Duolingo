package CsvGenerator;

import entity.Quiz;
import entity.Unit;
import entity.User;

import java.io.*;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WriteUsersFile {
    public static void writeIntoUsersFile(List<User> users) {

        try {
            BufferedWriter outStream= new BufferedWriter(new FileWriter("users.csv"));

            for (User user : users) {

                Quiz last = user.getLastDoneQuiz();
                int solvedQuizzes = 0;
                List<Unit> units = user.getLanguage().getUnits();
                for (Unit temp: units) {
                    if (temp.getQuizList().contains(last)) {
                        solvedQuizzes += temp.getQuizList().indexOf(last);
                        break;
                    }
                    solvedQuizzes+=temp.getQuizList().size();
                }

                outStream.write(user.getUserName());
                outStream.write(",");
                outStream.write(user.getPassword());
                outStream.write(",");
                outStream.write(user.getLanguage().getName());
                outStream.write(",");
                outStream.write(String.valueOf(solvedQuizzes));
                outStream.write(",");

                outStream.write(String.valueOf(user.getPoints()));
                outStream.newLine();

            }
            outStream.close();

            System.out.println("User list has been written to the file.");
        } catch (IOException ex) {
            throw new RuntimeException(ex);

//        csvWriter.append(data);
//
//            csvWriter.close();
//            outputStreamWriter.close();
//            fileOutputStream.close();


//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        }
    }
}
