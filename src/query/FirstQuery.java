package query;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class FirstQuery {
    public static void getFirstQuery(List<User> userList) {
        int max = 0;

        List<User> users = new ArrayList<>();
        for (User temp: userList) {
            if (temp.getPoints() > max) {
                max = temp.getPoints();
            }
        }
        for (User temp: userList) {
            if (temp.getPoints()==max) {
                users.add(temp);
            }
        }

        System.out.print("1- ");

        for (User temp: users) {
            System.out.print(temp.getUserName() + " " + temp.getPoints() + " points");
        }
    }
}
