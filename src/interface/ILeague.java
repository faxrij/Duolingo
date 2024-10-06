package interface;

import entity.User;

import java.util.List;

public interface ILeague {
    List<User> getTop3users();
    void addUser(User user);
}
