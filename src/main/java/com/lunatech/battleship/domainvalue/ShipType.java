package com.lunatech.battleship.domainvalue;

public enum ShipType
{

    ANGLE(4, 3), X_WING(5, 3), A_WING(4, 3), B_WING(5, 3), S_WING(3, 3);

    private int xSize;
    private int ySize;


    ShipType(int xSize, int ySize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
    }


    public int getxSize()
    {
        return this.xSize;
    }


    public int getySize()
    {
        return this.ySize;
    }

}
