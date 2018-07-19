package com.lunatech.battleship.datatransferobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.lunatech.battleship.domainobject.Board;

@Entity
public class PlayerDTO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    private String userId;

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


    public ZonedDateTime getDateCreated()
    {
        return this.dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
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


    public Boolean getAutoPilot()
    {
        return this.autoPilot;
    }


    public void setAutoPilot(Boolean autoPilot)
    {
        this.autoPilot = autoPilot;
    }

}
