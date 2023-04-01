package query;

import entity.Language;
import entity.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FourthQuery {
    public void getFourthQuery(List<Language> languages) {
        List<Integer> integerList = new ArrayList<>();
        int quizNum = 0;

        for (Language temp: languages) {
            for (Unit unit : temp.getUnits()) {
                quizNum += unit.getQuizList().size();
            }
            integerList.add(quizNum);
            quizNum = 0;
        }

        int maxNumOfQuiz = Collections.max(integerList);
        int indexOf = integerList.indexOf(maxNumOfQuiz);

        System.out.print("\n4- ");
        System.out.print(languages.get(indexOf).getName() + " " + maxNumOfQuiz + " Quizzes");

    }
}
