package Interface;

import entity.User;

import java.util.List;

public interface ILeague {
    public List<User> getTop3users();
    public void addUser(User user);
    public void removeUser(User user);
}
