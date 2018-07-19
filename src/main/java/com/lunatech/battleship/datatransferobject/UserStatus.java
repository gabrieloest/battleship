package com.lunatech.battleship.datatransferobject;

public class UserStatus
{

    private String userId;
    private String[] board;


    public String getUserId()
    {
        return this.userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId;
    }


    public String[] getBoard()
    {
        return this.board;
    }


    public void setBoard(String[] board)
    {
        this.board = board;
    }

}
