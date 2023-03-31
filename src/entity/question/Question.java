package entity.question;

public abstract class Question {
    private final int point;

    public Question(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
    public abstract String toString();
    public static Question createQuestion(String type) {
        switch (type) {
            case "R":
                return new ReadingQuestion();
            case "L":
                return new ListeningQuestion();
            case "S":
                return new SpeakingQuestion();
            case "W":
                return new WordMatchingQuestion();
            default:
                throw new IllegalArgumentException("Invalid question type");
        }
    }
}
