import CsvGenerator.WriteLanguagesFile;
import CsvGenerator.WriteUsersFile;
import Reader.ReadFile;
import entity.Language;
import entity.Unit;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mediator {
    public static void mainMethod () {
        WriteLanguagesFile.generate();
        List<User> userList = ReadFile.readUsersFile("users.csv");
        List<Language> languages = new ArrayList<>();

        for (User user: userList) {
            if(!languages.contains(user.getLanguage())) {
                languages.add(user.getLanguage());
            }
        }
        for (Language language : languages) {
            List<Unit> units = language.getUnits();

            int quizznum = 0;
            for (Unit temp : units) {
                quizznum += temp.getQuizList().size();
            }

            List<User> users = language.getUsers();

            for (User user : users) {

                Random rand = new Random();
                int quizToBeTaken = rand.nextInt(quizznum - 6) + 6;
                Unit userUnit = user.getUnit();

                assert userUnit != null;

                int toBeIndex = userUnit.getQuizList().indexOf(user.getLastDoneQuiz());
                int currentQuizSize = userUnit.getQuizList().size();

                if (currentQuizSize - (toBeIndex + quizToBeTaken) > 0) {

                    setUnitAndQuizWhenNoOverlap(user, quizToBeTaken, userUnit, toBeIndex);
                }
                else {
                    setUnitAndQuizWhenOverlap(language, user, quizToBeTaken, userUnit, toBeIndex);
                }
            }
        }
        WriteUsersFile.writeIntoUsersFile(userList);
    }

    private static void setUnitAndQuizWhenNoOverlap(User user, int quizToBeTaken, Unit userUnit, int toBeIndex) {
        for (int i = 0; i < quizToBeTaken; i++) {

            user.takeQuiz(userUnit.getQuizList().get(toBeIndex + i));
        }

        user.setToBeDoneQuiz(userUnit.getQuizList().get(toBeIndex + quizToBeTaken));
        user.checkIfUserGetsToNextLeague();
    }

    private static void setUnitAndQuizWhenOverlap(Language language, User user, int quizToBeTaken, Unit userUnit, int toBeDoneQuizIndex) {

        int counter = 0;

        while (quizToBeTaken > 0) {

            if (toBeDoneQuizIndex + counter == userUnit.getQuizList().size()) {
                userUnit = language.getUnits().get(language.getUnits().indexOf(userUnit) + 1);
                counter = 0;
                toBeDoneQuizIndex = 0;

                user.setUnit(userUnit);
            }

            user.takeQuiz(userUnit.getQuizList().get(toBeDoneQuizIndex + counter));
            user.setToBeDoneQuiz(userUnit.getQuizList().get(toBeDoneQuizIndex + counter));
            counter++;
            quizToBeTaken--;

        }

        user.checkIfUserGetsToNextLeague();
    }
}

