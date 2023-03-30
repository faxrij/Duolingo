package Interface;

import entity.Unit;
import entity.User;

import java.util.List;

public interface ILanguage {
    void addUnit(Unit unit);
    List<User> whoIsMostAdvanced();
}
