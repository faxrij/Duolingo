package Interface;

import entity.Quiz;

public interface IUser {
    public void takeQuiz(Quiz quiz);
    public int whichPlaceInTopRanking();
    public void checkIfUserGetsToNextLeague();
}
