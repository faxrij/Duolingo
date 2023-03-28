package entity;

import Interface.ILanguage;

import java.util.ArrayList;
import java.util.List;

public class Language implements ILanguage {
    private String name;
    private List<Unit> units;

    public Language(String name) {
        this.name = name;
        this.units = new ArrayList<>();
    }

    public Language() {}


    public String getName() {
        return name;
    }

    public List<Unit> getUnitList() {
        return units;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    @Override
    public Unit getOrCreateUnit(String unitName) {
        for (Unit temp: units) {
            if (temp.getName().equals(unitName)) {
                return temp;
            }
        }

        Unit unit = new Unit(unitName);
        this.addUnit(unit);
        return unit;

    }
}
