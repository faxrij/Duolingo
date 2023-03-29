package entity.league;

import entity.User;

public class SapphireLeague extends League{
    public SapphireLeague() {
        super("Sapphire");
    }

    @Override
    public boolean checkRequirements(User user) {
        if (user.getLeague().getClass() != GoldLeague.class) {
            return false;
        }
        return user.whichPlaceInTopRanking() <= 5 && user.getStreak()>=7;
    }
}
