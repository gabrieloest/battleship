package com.lunatech.battleship.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lunatech.battleship.domainobject.Board;
import com.lunatech.battleship.domainobject.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDTO
{

    @JsonIgnore
    private Long id;

    private User user;

    private String fullname;

    private Board board;

    private Boolean myTurn;

    private Boolean autoPilot;


    public Long getId()
    {
        return this.id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public User getUser()
    {
        return this.user;
    }


    public void setUserId(User user)
    {
        this.user = user;
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


    public Boolean getAutoPilot()
    {
        return this.autoPilot;
    }


    public void setAutoPilot(Boolean autoPilot)
    {
        this.autoPilot = autoPilot;
    }

}
