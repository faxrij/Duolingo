package entity;

import Interface.IUser;
import entity.league.*;
import entity.question.Question;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class User implements IUser, Comparable<User>, Serializable {
    private final String userName;
    private final String password;
    private League league;
    private int points;
    private Language language;

    private int streak;

    private Unit unit;

    private Quiz toBeDoneQuiz;

    public Quiz getLastDoneQuiz() {
        return toBeDoneQuiz;
    }
    public void setToBeDoneQuiz(Quiz toBeDoneQuiz) {
        this.toBeDoneQuiz = toBeDoneQuiz;
    }
    public League getLeague() {
        return league;
    }

    public Language getLanguage() {
        return language;
    }

    public String getPassword() {
        return password;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public User() {
        this.userName = null;
        this.password = null;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void setLeague(League league) {
        this.league = league;
    }


    public Unit getUnit() {
        return this.unit;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
    public int getStreak() {
        return streak;
    }

    public int getPoints() {
        return points;
    }


    @Override
    public void takeQuiz(Quiz quiz) {
        Random rand = new Random();
        List<Question> questions = quiz.getQuestionList();

        for (Question temp: questions) {
            if (rand.nextBoolean()) {
                this.points+= temp.getPoint();
            }
        }
    }

    @Override
    public int whichPlaceInTopRanking() {
        List<User> users = this.getLeague().getUserList();
        return users.indexOf(this);
    }

    @Override
    public void checkIfUserGetsToNextLeague() {
        League currentLeague = this.getLeague();

        switch (currentLeague.getName()) {
            case "Bronze":
                LeagueFactory.getSilverLeague(this);
                break;
            case "Silver":
                LeagueFactory.getGoldLeague(this);
                break;
            case "Gold":
                LeagueFactory.getSapphireLeague(this);
                break;
            case "Sapphire":
                LeagueFactory.getRubyLeague(this);
                break;
            default:
                // user is already in the highest league
                break;
        }
    }

    @Override
    public int compareTo(User u){
        return Integer.compare(u.points, this.points);
    }
}
