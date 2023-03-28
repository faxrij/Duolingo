package entity.question;

import helper.CreateRandomString;

public class ReadingQuestion extends Question {
    private String stringInEnglish;
    private String stringInSelectedLanguage;
    public ReadingQuestion() {
        super(10);
        this.stringInSelectedLanguage = CreateRandomString.getAlphaNumericString(5);
        this.stringInEnglish = CreateRandomString.getAlphaNumericString(5);
    }

    @Override
    public String toString() {
        return "Reading Question -> word in English is " + stringInEnglish + " entered is " + stringInSelectedLanguage;
    }
}
