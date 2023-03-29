package entity;

import Interface.ILanguage;
import entity.league.League;

import java.util.ArrayList;
import java.util.List;

public class Language implements ILanguage {
    private String name;
    private List<Unit> units;
    private List<League> leagues;
    private List<User> users;

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public void addLeague(League league) {
        leagues.add(league);
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

    public Language() {}

    public String getName() {
        return name;
    }

    public List<Unit> getUnitList() {
        return units;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addUnit(Unit unit) {
        units.add(unit);
    }

}
