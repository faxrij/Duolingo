package helper;

import entity.Language;

import java.util.List;

public class GetLanguageFromList {
    public static int getLanguage(List<Language> languageList, String languageName) {
        boolean found = false;
        int index = 0;
        for (int i=0; i<languageList.size();i++) {
            if (languageList.get(i).getName().equals(languageName)) {
                index = i;
                found = true;
                break;
            }
        }
        if(found) return index;

        return -1;
    }
}
