import CsvGenerator.WriteLanguagesFile;
import CsvGenerator.WriteUsersFile;
import Reader.ReadFile;
import entity.Language;
import entity.Unit;
import entity.User;
import query.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mediator {
    public void mainMethod () {
        WriteLanguagesFile writeLanguagesFile = new WriteLanguagesFile();
        writeLanguagesFile.write();

        ReadFile readFile = new ReadFile();

        List<User> userList = readFile.readUsersFile("users.csv");
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
        WriteUsersFile writeUsersFile = new WriteUsersFile();

        writeUsersFile.writeIntoUsersFile(userList);

        for (User temp: userList) {
            temp.checkIfUserGetsToNextLeague();
        }
        // set user to next league, but no deletion. If we make user go higher and remove past league, it will make other users go high as well

        for (Language temp : languages) {
            for (User user: temp.getUsers()) {
                temp.checkIfUserExistsIn2Leagues(user); // DELETE OVERFLOWING USERS HERE
            }
        }

        FirstQuery firstQuery = new FirstQuery();
        firstQuery.getFirstQuery(userList);

        SecondQuery secondQuery = new SecondQuery();
        secondQuery.getSecondQuery(languages);

        ThirdQuery thirdQuery = new ThirdQuery();
        thirdQuery.getThirdQuery(languages);

        FourthQuery fourthQuery = new FourthQuery();
        fourthQuery.getFourthQuery(languages);

        FifthQuery fifthQuery = new FifthQuery();
        fifthQuery.getFifthQuery(languages);
    }


    private void setUnitAndQuizWhenNoOverlap(User user, int quizToBeTaken, Unit userUnit, int toBeIndex) {
        for (int i = 0; i < quizToBeTaken; i++) {

            user.takeQuiz(userUnit.getQuizList().get(toBeIndex + i));
        }

        user.setToBeDoneQuiz(userUnit.getQuizList().get(toBeIndex + quizToBeTaken));
    }

    private void setUnitAndQuizWhenOverlap(Language language, User user, int quizToBeTaken, Unit userUnit, int toBeDoneQuizIndex) {

        int counter = 0;

        while (quizToBeTaken > 0) {
            if (userUnit.equals(language.getUnits().get(language.getUnits().size()-1))) {
                break;
            }
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
    }
}