package com.lunatech.battleship.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Player
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    private User user;

    private String fullname;

    private Board board;

    private Board opponentBoard;

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


    public ZonedDateTime getDateCreated()
    {
        return this.dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public User getUser()
    {
        return this.user;
    }


    public void setUser(User user)
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


    public Board getOpponentBoard()
    {
        return this.opponentBoard;
    }


    public void setOpponentBoard(Board opponentBoard)
    {
        this.opponentBoard = opponentBoard;
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
