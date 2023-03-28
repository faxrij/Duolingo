package entity;

public class User {
    private String userName;
    private String password;
    private Language language;
    private int points;

    private int streak;

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

    public void setLanguage(Language language) {
        this.language = language;
    }
    public Language getLanguage() {
        return this.language;
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
}
