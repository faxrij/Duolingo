package query;

import entity.Language;
import entity.User;
import helper.GetLanguageFromList;

import java.util.List;

public class FifthQuery {
    public void getFifthQuery(List<Language> languages) {
        int index = GetLanguageFromList.getLanguage(languages, "Italian");
        if (index != -1) {
            List<User> asd = languages.get(index).getLeagues().get(1).getTop3users();

            System.out.print("\n"+"5- Italian Silver League Top " + asd.size() + ": ");
            int i =1;
            for (User a:asd) {
                System.out.print(i + "." + a.getUserName() + " ");
                i++;
            }
        }

    }
}
