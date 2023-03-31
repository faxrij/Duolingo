package entity.league;

import entity.User;

public class GoldLeague extends League{
    public GoldLeague() {
        super("Gold");
    }

    @Override
    public boolean checkRequirements(User user) {
        if (user.getLeague().getClass() != GoldLeague.class) {
            return false;
        }

        return user.whichPlaceInTopRanking() <= 10;
    }
}
