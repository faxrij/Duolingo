package entity.question;

import java.util.Random;

public class SpeakingQuestion extends Question {
    private int listenedAudio;
    private int spokenAudio;

    public int getListenedAudio() {
        return listenedAudio;
    }

    public int getSpokenAudio() {
        return spokenAudio;
    }

    public SpeakingQuestion() {
        super(8);
        Random rand = new Random();
        this.listenedAudio = rand.nextInt(5) + 1;
        this.spokenAudio = rand.nextInt(5) + 1;
    }

    @Override
    public String toString() {
        return "Speaking Question -> length of first audio is " + listenedAudio + " spoken is " + spokenAudio;
    }
}
