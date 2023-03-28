package entity.question;

import helper.CreateRandomString;

import java.util.Random;

public class ListeningQuestion extends Question {
    private String string;

    public String getString() {
        return string;
    }

    public int getAudio() {
        return audio;
    }

    private int audio;
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
