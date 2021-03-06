package com.lunatech.battleship.domainvalue;

public enum RuleType
{

    STANDARD("standard"), SUPER_CHARGE("super_charge"), DESPERATION("desperation"), X_SHOT("x_shot");

    private String description;


    RuleType(String description)
    {
        this.description = description;
    }


    public String description()
    {
        return this.description;
    }


    RuleType getValue(String value)
    {
        for (RuleType e : RuleType.values())
        {
            if (e.description.equals(value))
            {
                return e;
            }
        }
        return null;
    }
}
