import CsvGenerator.WriteFile;
import Reader.ReadFile;
import entity.Language;
import entity.User;


import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        List<User> userList = ReadFile.readUsersFile("users.csv");
        WriteFile.generate();

        List<Language> languages = ReadFile.readLanguageFile("languages.csv");

        for (User temp : userList) {
            Random rand = new Random();
            Language language = languages.get(rand.nextInt(4));
            temp.setLanguage(language);
//            language.set
        }

        for (Language temp: languages) {
            System.out.println(temp.getUnitList().get(0).getQuizList().get(0).getQuestionList().get(5).toString());
        }
    }
}