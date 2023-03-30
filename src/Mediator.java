import CsvGenerator.WriteLanguagesFile;
import CsvGenerator.WriteUsersFile;
import Reader.ReadFile;
import entity.Language;
import entity.Unit;
import entity.User;

import java.util.ArrayList;
import java.util.Collections;
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

        int max = 0;

        List<User> users = new ArrayList<>();
        for (User temp: userList) {
            if (temp.getPoints() > max) {
                max = temp.getPoints();
            }
        }
        for (User temp: userList) {
            if (temp.getPoints()==max) {
                users.add(temp);
            }
        }

        System.out.print("1- ");

        for (User temp: users) {
            System.out.print(temp.getUserName() + " " + temp.getPoints() + " points");
        }

        Language german = languages.get(2);
        List<User> advancedUsers = german.whoIsMostAdvanced();

        System.out.print("\n2- ");
        for (User temp: advancedUsers) {
            System.out.print(temp.getUserName() + " Unit " + temp.getUnit().getUnitNum() + " ");
        }

        System.out.print("\n3- ");

        List<Language> languageList = new ArrayList<>();
        max = 0;
        for (Language temp: languages) {
            if (temp.getUnits().size() > max) {
                max = temp.getUnits().size();
            }
        }

        for (Language temp: languages) {
            if (temp.getUnits().size()==max) {
                languageList.add(temp);
            }
        }

        for (Language temp: languageList) {
            System.out.print(temp.getName() + " " + temp.getUnits().size() + " Units");
        }


        List<Integer> integerList = new ArrayList<>();
        int quizNum = 0;

        for (Language temp: languages) {
            for (Unit unit : temp.getUnits()) {
                quizNum += unit.getQuizList().size();
            }
            integerList.add(quizNum);
            quizNum = 0;
        }

        int maxNumOfQuiz = Collections.max(integerList);
        int indexOf = integerList.indexOf(maxNumOfQuiz);

        System.out.print("\n4- ");
        System.out.print(languages.get(indexOf).getName() + " " + maxNumOfQuiz + " Quizzes");


        List<User> asd = languages.get(1).getLeagues().get(1).getTop3users();
        System.out.print("\n"+"5- Italian Silver League Top " + asd.size() + ": ");
        int i =1;
        for (User a:asd) {
            System.out.print(i + "." + a.getUserName() + " ");
            i++;
        }
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

        user.checkIfUserGetsToNextLeague();
    }
}

