package query;

import entity.Language;

import java.util.ArrayList;
import java.util.List;

public class ThirdQuery {
    public static void getThirdQuery(List<Language> languages) {
        System.out.print("\n3- ");

        List<Language> languageList = new ArrayList<>();
        int max = 0;
        for (Language temp: languages) {
            if (temp.getUnits().size() > max) {
                max = temp.getUnits().size();
            }
        }

        for (Language temp: languages) {
            if (temp.getUnits().size()==max) {
                languageList.add(temp);
            }
        }

        for (Language temp: languageList) {
            System.out.print(temp.getName() + " " + temp.getUnits().size() + " Units");
        }
    }
}
