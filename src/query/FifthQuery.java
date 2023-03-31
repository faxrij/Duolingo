package query;

import entity.Language;
import entity.User;

import java.util.List;

public class FifthQuery {
    public static void getFifthQuery(List<Language> languages) {
        List<User> asd = languages.get(1).getLeagues().get(1).getTop3users();
        System.out.print("\n"+"5- Italian Silver League Top " + asd.size() + ": ");
        int i =1;
        for (User a:asd) {
            System.out.print(i + "." + a.getUserName() + " ");
            i++;
        }
    }
}
