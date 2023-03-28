package entity;

import entity.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String name;
    private List<Question> questionList;

    public Quiz(String name) {
        this.name = name;
        this.questionList = new ArrayList<>();
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> question) {
        this.questionList = question;
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }
}
