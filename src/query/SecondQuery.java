package query;

import entity.Language;
import entity.User;

import java.util.List;

public class SecondQuery {
    public static void getSecondQuery(List<Language> languages) {
        Language german = languages.get(2);
        List<User> advancedUsers = german.whoIsMostAdvanced();

        System.out.print("\n2- ");
        for (User temp: advancedUsers) {
            System.out.print(temp.getUserName() + " Unit " + temp.getUnit().getUnitNum() + " ");
        }
    }
}
