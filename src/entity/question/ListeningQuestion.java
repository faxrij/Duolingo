package entity.question;

import helper.CreateRandomString;

import java.util.Random;

public class ListeningQuestion extends Question {
    private final String string;
    private final int audio;

    public String getString() {
        return string;
    }

    public ListeningQuestion() {
        super(7);
        Random rand = new Random();
        this.string = CreateRandomString.getAlphaNumericString(5);
        this.audio = rand.nextInt(5) + 1;
    }

    @Override
    public String toString() {
        return "Listening Question -> word is " + string + " audio is " + audio;
    }
}
