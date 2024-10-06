package interface;

import entity.Quiz;

public interface IUser {
    void takeQuiz(Quiz quiz);
    int whichPlaceInTopRanking();
    void checkIfUserGetsToNextLeague();
}
