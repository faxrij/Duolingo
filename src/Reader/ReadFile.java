package Reader;

import entity.Language;
import entity.Quiz;
import entity.Unit;
import entity.User;

import java.io.*;
import java.util.*;

import static entity.question.Question.createQuestion;

public class ReadFile {

    public static List<User> readUsersFile(String filename) {
        List<User> users = new ArrayList<>(); //create a list
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String fileLine = myReader.nextLine();
                String[] line = fileLine.split(";");     //separate lines for get names,password attributes.
                String name = line[0];                   //name
                String password = line[1];      //password

                User user = new User(name, password);
                users.add(user); //add to the list.
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return users;
    }

    public static List<Language> readLanguageFile(String filename) {
        List<Language> languages = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

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

    private static void parsingUnitPart(String[] splitPart, Language language) {
        for (int i = 1; i < splitPart.length; i++) {
            String token = splitPart[i].trim();

            // Check if a unit
            if (token.startsWith("Unit")) {
                Unit unit = new Unit(token);

                language.addUnit(unit);

                // Get quizzes for unit part
                if (i + 1 < splitPart.length && !splitPart[i + 1].startsWith("Unit")) {
                    // create quizzes
                    String[] quizTokens = splitPart[i + 1].split(",");
                    Quiz quiz = new Quiz(quizTokens[0]);

                    // Parsing Questions
                    String[] questionTokens = splitPart[i + 2].split(";");

                    parsingQuestionPart(quiz, questionTokens);
                    unit.addQuiz(quiz);
                    i++;
                }
            }
        }
    }

    private static void parsingQuestionPart(Quiz quiz, String[] questionList) {
        for (String question : questionList) {
            String type = question.substring(question.length()-1);
            int num = Integer.parseInt(question.substring(0, question.length() - 1));
            for (int l = 0; l<num; l++) {
                quiz.addQuestion(createQuestion(type));
            }

        }
    }
}
