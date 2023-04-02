package entity.league;

import entity.User;

public class LeagueChecker {
    public static void getSilverLeague(User user) {
        League league = user.getLanguage().getLeagues().get(1);
        if (league.checkRequirements(user)) {

            user.setLeague(user.getLanguage().getLeagues().get(1));
            league.addUser(user);
        }
    }

    public static void getGoldLeague(User user) {
        League league = user.getLanguage().getLeagues().get(2);
        if (league.checkRequirements(user)) {

            user.setLeague(user.getLanguage().getLeagues().get(2));
            league.addUser(user);
        }
    }

    public static void getSapphireLeague(User user) {
        League league = user.getLanguage().getLeagues().get(3);
        if (league.checkRequirements(user)) {

            user.setLeague(user.getLanguage().getLeagues().get(3));
            league.addUser(user);
        }
    }

    public static void getRubyLeague(User user) {
        League league = user.getLanguage().getLeagues().get(3);
        if (league.checkRequirements(user)) {

            user.setLeague(user.getLanguage().getLeagues().get(3));
            league.addUser(user);
        }
    }
}
