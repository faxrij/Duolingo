package Reader;

import entity.*;
import entity.league.*;

import java.io.*;
import java.util.*;

import static entity.question.Question.createQuestion;

public class ReadFile {

    public List<User> readUsersFile(String filename) {
        List<User> users = new ArrayList<>(); //create a list

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            List<Language> languageList = readLanguageFile();

            for (Language lang: languageList) {
                lang.setLeagues(setLeague());
            }

            while (myReader.hasNextLine()) {
                String fileLine = myReader.nextLine();
                String[] line = fileLine.split(";");     //separate lines for get names,password, and other attributes.
                String name = line[0];                   //name
                String password = line[1];              //password
                User user = new User(name, password);
                Random rand = new Random();

                if (line.length > 2) {
                    for (Language temp: languageList) {
                        if (line[2].equals(temp.getName())) {
                            user.setLanguage(temp);
                            temp.addUser(user);
                            break;
                        }
                    }
                    List<League> list = user.getLanguage().getLeagues();
                    League randomLeague = list.get(rand.nextInt(list.size()));
                    randomLeague.addUser(user);
                    user.setLeague(randomLeague);

                    int solvedQuizzes = Integer.parseInt(line[4]);
                    List<Unit> units = user.getLanguage().getUnits();

                    Quiz quiz = null;

                    for (Unit unit: units) {
                        if (solvedQuizzes < unit.getQuizList().size()) {
                            quiz = unit.getQuizList().get(solvedQuizzes);
                            break;
                        }
                        else {
                            solvedQuizzes -= unit.getQuizList().size();
                        }
                    }

                    user.setToBeDoneQuiz(quiz);

                    for (Unit temp: user.getLanguage().getUnits()) {
                        if (temp.getName().equals(line[3])) {
                            user.setUnit(temp);
                            break;
                        }
                    }
                    user.setPoints(Integer.parseInt(line[5]));
                }
                else {
                    firstRunOnly(user, languageList);
                }

                user.setStreak(rand.nextInt(365)+1);
                users.add(user); //add to the list.
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return users;
    }

    public static List<Language> readLanguageFile() {
        List<Language> languages = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("languages.csv"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitPart = line.split(",");

                String languageName = splitPart[0].trim();
                Language language = null;

                // Check if language already exists
                for (Language l : languages) {
                    if (l.getName().equals(languageName)) {
                        language = l;
                        break;
                    }
                }

                // Create new language if it doesn't exist
                if (language == null) {
                    language = new Language(languageName);
                    languages.add(language);
                }

                // Loop through remaining part to get units and quizzes
                parsingUnitPart(splitPart, language);
            }
            reader.close();
            return languages;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void firstRunOnly(User temp, List<Language> languages) {
        Random rand = new Random();
        for (Language tempLang: languages) {
            tempLang.setLeagues(setLeague());
        }

        Language language = languages.get(rand.nextInt(4));

        language.addUser(temp);
        temp.setLanguage(language);
        temp.setLeague(language.getLeagues().get(0));
        temp.getLeague().addUser(temp);
        temp.setUnit(language.getUnits().get(0));
        temp.setToBeDoneQuiz(language.getUnits().get(0).getQuizList().get(0));

    }

    private static List<League> setLeague() {
        League bronzeLeague = new BronzeLeague();
        League silverLeague = new SilverLeague();
        League goldLeague = new GoldLeague();
        League sapphireLeague = new SapphireLeague();
        League rubyLeague = new RubyLeague();

        List<League> leagues = new ArrayList<>();
        leagues.add(bronzeLeague);
        leagues.add(silverLeague);
        leagues.add(goldLeague);
        leagues.add(sapphireLeague);
        leagues.add(rubyLeague);

        return leagues;
    }

    private static void parsingUnitPart(String[] splitPart, Language language) {
        Quiz currentQuiz;
        Unit currentUnit = null;

        for (int i = 1; i < splitPart.length; i++) {
            String token = splitPart[i].trim();

            // Check if a unit
            if (token.startsWith("Unit")) {
                currentUnit = new Unit(token);
                // Replace Unit56 with 56 to get unitNumber
                currentUnit.setUnitNum(Integer.parseInt(token.replace("Unit","")));
                language.addUnit(currentUnit);

                // Get quizzes for unit part
            }
            else if (token.startsWith("Quiz")) {

                // Create a new quiz and get its questions
                String[] quizTokens = splitPart[i+1].split(",");
                currentQuiz = new Quiz(token);

                parsingQuestionPart(currentQuiz, quizTokens);
                assert currentUnit != null;
                currentUnit.addQuiz(currentQuiz);
            }
        }
    }

    private static void parsingQuestionPart(Quiz quiz, String[] questionList) {
        String reading;
        String listening;
        String speaking;
        String writing;

        List<String> strings = new ArrayList<>();
        for (String question : questionList) {
            String[] splitArray = question.split(";");
            reading = splitArray[0];
            listening = splitArray[1];
            speaking = splitArray[2];
            writing = splitArray[3];

            strings.add(reading);
            strings.add(listening);
            strings.add(speaking);
            strings.add(writing);
            break;
        }
        for (String question : strings) {

            String type = question.substring(question.length() - 1);
            int num = Integer.parseInt(question.substring(0, question.length() - 1));
            for (int l = 0; l < num; l++) {
                quiz.addQuestion(createQuestion(type));
            }
        }
    }
}
