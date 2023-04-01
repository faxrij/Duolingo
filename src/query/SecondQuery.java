package query;

import entity.Language;
import entity.User;
import helper.GetLanguageFromList;

import java.util.List;

public class SecondQuery {
    public void getSecondQuery(List<Language> languages) {
        int index = GetLanguageFromList.getLanguage(languages, "German");

        if (index != -1) {
            Language german = languages.get(index);
            List<User> advancedUsers = german.whoIsMostAdvanced();

            System.out.print("\n2- ");
            for (User temp: advancedUsers) {
                System.out.print(temp.getUserName() + " Unit " + temp.getUnit().getUnitNum() + " ");
            }
        }
    }
}
