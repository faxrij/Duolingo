package entity.question;

import helper.CreateRandomString;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordMatchingQuestion extends Question {
    private final Map<String, String> questionsMap;

    public WordMatchingQuestion() {
        super(5);
        Random random = new Random();
        questionsMap = new HashMap<>();

        int val = random.nextInt(4) + 4;
        for (int i = 0; i<val; i++) {
            String actualString = CreateRandomString.getAlphaNumericString(5);
            String providedString = CreateRandomString.getAlphaNumericString(5);
            questionsMap.put(actualString, providedString);
        }
    }

    @Override
    public String toString() {
        return "WordMatching Question -> it has size of " + questionsMap.size();
    }
}
