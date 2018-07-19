package com.lunatech.battleship.domainvalue;

public enum GameStatusType
{

    WINNER("winner"), PLAYER_TURN("player_turn");

    private String description;


    GameStatusType(String description)
    {
        this.description = description;
    }


    public String description()
    {
        return this.description;
    }


    GameStatusType getValue(String value)
    {
        for (GameStatusType e : GameStatusType.values())
        {
            if (e.description.equals(value))
            {
                return e;
            }
        }
        return null;
    }
}
