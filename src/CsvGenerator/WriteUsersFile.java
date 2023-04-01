package CsvGenerator;

import entity.Quiz;
import entity.Unit;
import entity.User;

import java.io.*;


import java.util.List;

public class WriteUsersFile {
    public void writeIntoUsersFile(List<User> users) {

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
                    solvedQuizzes += temp.getQuizList().size();
                }

                outStream.write(user.getUserName());
                outStream.write(";");
                outStream.write(user.getPassword());
                outStream.write(";");
                outStream.write(user.getLanguage().getName());
                outStream.write(";");
                outStream.write(user.getUnit().getName());
                outStream.write(";");
                outStream.write(String.valueOf(solvedQuizzes));
                outStream.write(";");
                outStream.write(String.valueOf(user.getPoints()));
                outStream.newLine();

            }
            outStream.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }
    }
}
