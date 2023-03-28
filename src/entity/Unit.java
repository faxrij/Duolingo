package entity;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    private String name;
    private List<Quiz> quizList;

    public Unit(String name) {
        this.name = name;
        this.quizList = new ArrayList<>();
    }
    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
    public void addQuiz(Quiz quiz) {
        quizList.add(quiz);
    }
    public String getName() {
        return this.name;
    }
    public List<Quiz> getQuizList() {
        return this.quizList;
    }

}
