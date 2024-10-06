package csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import Constant.Constants;

public class WriteLanguagesFile {
    public void write() {
        try {
            File file = new File("languages.csv");
            if(file.length()!=0) {
                return;
            }
            FileWriter writer = new FileWriter("languages.csv");

            Random rand = new Random();

            for (String language : Constants.LANGUAGES) {
                writer.append(language);

                int numUnits = rand.nextInt(Constants.MAX_UNITS - Constants.MIN_UNITS) + Constants.MIN_UNITS;

                for (int i = 1; i <= numUnits; i++) {
                    writer.append(",Unit").append(String.valueOf(i));
                    int numQuizzes = rand.nextInt(Constants.MAX_QUIZZES - Constants.MIN_QUIZZES) + Constants.MIN_QUIZZES;

                    for (int j = 1; j <= numQuizzes; j++) {

                        while (true) {
                            if (setUpRandomQuestions(writer, rand, j)) continue;
                            break;
                        }
                    }
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean setUpRandomQuestions(FileWriter writer, Random rand, int j) throws IOException {
        int readingQuestions = rand.nextInt(16);

        int listeningQuestions = rand.nextInt(16 - readingQuestions);

        if (checkIfNumberOfQuestionsAreMoreThan15(readingQuestions, listeningQuestions, 0, 0)) return true;

        int speakingQuestions = rand.nextInt(16 - readingQuestions - listeningQuestions);

        if (checkIfNumberOfQuestionsAreMoreThan15(readingQuestions, listeningQuestions, speakingQuestions, 0)) return true;

        int wordMatchingQuestions = rand.nextInt(16 - readingQuestions - listeningQuestions - speakingQuestions);

        if (checkIfNumberOfQuestionsAreMoreThan15(readingQuestions, listeningQuestions, speakingQuestions, wordMatchingQuestions)) return true;

        writeIntoFile(writer, j, readingQuestions, listeningQuestions, speakingQuestions, wordMatchingQuestions);
        return false;
    }

    private boolean checkIfNumberOfQuestionsAreMoreThan15(int readingQuestions, int listeningQuestions, int speakingQuestions, int wordMatchingQuestions) {
        return readingQuestions + listeningQuestions + speakingQuestions + wordMatchingQuestions > 15
                || readingQuestions + listeningQuestions + speakingQuestions + wordMatchingQuestions < 8
                ;
    }

    private void writeIntoFile(FileWriter writer, int j, int readingQuestions, int listeningQuestions, int speakingQuestions, int wordMatchingQuestions) throws IOException {
        writer.append(",Quiz").append(String.valueOf(j)).append(",")
                .append(String.valueOf(readingQuestions)).append("R").append(";")
                .append(String.valueOf(listeningQuestions)).append("L").append(";")
                .append(String.valueOf(speakingQuestions)).append("S").append(";")
                .append(String.valueOf(wordMatchingQuestions)).append("W");
    }
}
