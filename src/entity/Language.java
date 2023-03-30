package entity;

import Interface.ILanguage;
import entity.league.League;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Language implements ILanguage {
    private String name;
    private List<Unit> units;
    private List<League> leagues;
    private List<User> users;

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public void addLeague(League league) {
        if (!leagues.contains(league)) {
            leagues.add(league);
        }
    }
    public void addUser(User user) {
        users.add(user);
    }

    public List<Unit> getUnits() {
        return units;
    }
    public List<User> getUsers() {
        return users;
    }


    public List<League> getLeagues() {
        return leagues;
    }

    public Language(String name) {
        this.name = name;
        this.units = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    @Override
    public List<User> whoIsMostAdvanced() {
        int max = 0;
        List<Integer> integerList = new ArrayList<>();
        List<User> mostAdvancedNum = new ArrayList<>();

        for (User temp: users) {
            if (temp.getUnit().getUnitNum() >= max) {
                integerList.add(temp.getUnit().getUnitNum());
            }
        }
        max = Collections.max(integerList);
        for (User temp : users) {
            if (max == temp.getUnit().getUnitNum()) {
                mostAdvancedNum.add(temp);
            }
        }
        return mostAdvancedNum;
    }

}
