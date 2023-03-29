package entity.league;

import entity.User;

public class BronzeLeague extends League{
    public BronzeLeague() {
        super("Bronze");
    }

    @Override
    public boolean checkRequirements(User user) {
        return true;
    }
}
