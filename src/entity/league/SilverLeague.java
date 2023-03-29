package entity.league;

import entity.User;

public class SilverLeague extends League{
    public SilverLeague() {
        super("Silver");
    }

    @Override
    public boolean checkRequirements(User user) {
        if (user.getLeague().getClass() != BronzeLeague.class) {
            return false;
        }

        return user.whichPlaceInTopRanking() <= 15;
    }
}
