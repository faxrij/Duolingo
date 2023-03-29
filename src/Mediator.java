import CsvGenerator.WriteLanguagesFile;
import CsvGenerator.WriteUsersFile;
import Reader.ReadFile;
import entity.Language;
import entity.Unit;
import entity.User;

import java.util.List;
import java.util.Random;

public class Mediator {
    public static void mainMethod () {
        List<User> userList = ReadFile.readUsersFile("users.csv");
        WriteLanguagesFile.generate();

        List<Language> languages = ReadFile.readLanguageFile("languages.csv", userList);

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
                Unit userUnit = getUserUnit(units, user);

                assert userUnit != null;

                int lastQuizIndex = userUnit.getQuizList().indexOf(user.getLastDoneQuiz());
                int currentVar = userUnit.getQuizList().size();
                if (currentVar - (lastQuizIndex + quizToBeTaken) >= 0) {

                    setUnitAndQuizWhenNoOverlap(user, quizToBeTaken, userUnit, lastQuizIndex);
                }
                else {
                    setUnitAndQuizWhenOverlap(language, user, quizToBeTaken, userUnit, lastQuizIndex);
                }
            }
        }
        WriteUsersFile.writeIntoUsersFile(userList);
    }

    private static void setUnitAndQuizWhenNoOverlap(User user, int quizToBeTaken, Unit userUnit, int lastQuizIndex) {
        for (int i = 0; i < quizToBeTaken; i++) {

            user.takeQuiz(userUnit.getQuizList().get(lastQuizIndex + i));
        }

        user.setLastDoneQuiz(userUnit.getQuizList().get(lastQuizIndex + quizToBeTaken));
        user.checkIfUserGetsToNextLeague();
    }

    private static void setUnitAndQuizWhenOverlap(Language language, User user, int quizToBeTaken, Unit userUnit, int lastQuizIndex) {

        int numberOfQuizzesInCurrentUnit = userUnit.getQuizList().size();
        int differenceBetween = numberOfQuizzesInCurrentUnit - lastQuizIndex;
        int counter = 0;

        while (quizToBeTaken > 0) {

            if (numberOfQuizzesInCurrentUnit > 0) {

                user.takeQuiz(userUnit.getQuizList().get(lastQuizIndex + counter));
                user.setLastDoneQuiz(userUnit.getQuizList().get(lastQuizIndex + counter));
                numberOfQuizzesInCurrentUnit--;
                counter++;
                quizToBeTaken--;


            } else {
                userUnit = language.getUnits().get(language.getUnits().indexOf(userUnit) + 1);
                numberOfQuizzesInCurrentUnit = userUnit.getQuizList().size();
                counter = 0;

                user.setUnit(userUnit);
            }

//            numberOfQuizzesInCurrentUnit--;
//            counter++;
//            quizToBeTaken--;
        }

//        for (int i = 0; i < differenceBetween; i++) {
//
//            user.takeQuiz(userUnit.getQuizList().get(lastQuizIndex + i));
//        }
//
//        int indexOfNextUnit = language.getUnits().indexOf(userUnit) + 1;
//
//        user.setUnit(language.getUnits().get(indexOfNextUnit));
//
//        userUnit = user.getUnit();
//
//        for (int i = 0; i < quizToBeTaken - differenceBetween; i++) {
//            user.takeQuiz(userUnit.getQuizList().get(i));
//            lastQuizIndex = i;
//        }

//        user.setLastDoneQuiz(userUnit.getQuizList().get(lastQuizIndex));
        user.checkIfUserGetsToNextLeague();
    }

    private static Unit getUserUnit(List<Unit> units, User user) {
        for (Unit unit : units) {
            if (!unit.equals(user.getUnit())) {
                continue;
            }
            if (!unit.getQuizList().contains(user.getLastDoneQuiz())) {
                System.out.println("ERROR");
                break;
            }

            if (user.getLastDoneQuiz() == null) {
                System.out.println("ERROR");
                break;
            }
            return unit;
        }
        return null;
    }
}

