package entity.league;

import Interface.ILeague;
import entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class League implements ILeague{

    private final String name;
    private final List<User> userList;

    public League(String name) {
        this.name = name;
        userList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<User> getUserList() {
        Collections.sort(userList);
        return userList;
    }

    @Override
    public List<User> getTop3users() {
        if (getUserList().size()>=3) {
            return getUserList().subList(0,3);
        }
        return getUserList();
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public void removeUser(User user) {
        userList.remove(user);
    }

    public abstract boolean checkRequirements(User user);
}
