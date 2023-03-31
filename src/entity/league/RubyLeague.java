package entity.league;

import entity.User;

public class RubyLeague extends League{
    public RubyLeague() {
        super("Ruby");
    }

    @Override
    public boolean checkRequirements(User user) {
        if (user.getLeague().getClass() != SapphireLeague.class) {
            return false;
        }

        return (user.getPoints()>5000 || user.getUnit().getUnitNum() >= 10) && user.getStreak()>=30;
    }
}
