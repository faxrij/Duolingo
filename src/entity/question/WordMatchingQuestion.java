package entity.question;

import helper.CreateRandomString;

public class WordMatchingQuestion extends Question {
    private String actualString;
    private String providedString;

    public WordMatchingQuestion() {
        super(5);
        actualString = CreateRandomString.getAlphaNumericString(5);
        providedString = CreateRandomString.getAlphaNumericString(5);
    }

    @Override
    public String toString() {
        return "WordMatching Question -> actual word is " + actualString + " entered is " + providedString;
    }
}
