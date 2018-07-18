package com.lunatech.battleship.domainobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player
{

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String fullname;

    private Board board;

    private Boolean myTurn;


    public Long getId()
    {
        return this.id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUserId()
    {
        return this.userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId;
    }


    public String getFullname()
    {
        return this.fullname;
    }


    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }


    public Board getBoard()
    {
        return this.board;
    }


    public void setBoard(Board board)
    {
        this.board = board;
    }


    public Boolean getMyTurn()
    {
        return this.myTurn;
    }


    public void setMyTurn(Boolean myTurn)
    {
        this.myTurn = myTurn;
    }

}
