package entity.league;

import Interface.ILeague;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class League implements ILeague {

    private String name;
    private List<User> userList;

    public League(String name) {
        this.name = name;
        userList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<User> getUserList() {
        return userList.stream().sorted().collect(Collectors.toList());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public List<User> getTop3users() {
        return null;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public boolean removeUser(User user) {
        return userList.remove(user);
    }

    public abstract boolean checkRequirements(User user);
}
