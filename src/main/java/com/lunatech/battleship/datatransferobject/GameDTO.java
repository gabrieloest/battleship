package com.lunatech.battleship.datatransferobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.lunatech.battleship.domainobject.Player;
import com.lunatech.battleship.domainobject.Rule;

@Entity
public class GameDTO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    private String gameId;

    private Rule rule;

    private Player player1;

    private Player player2;


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


    public String getGameId()
    {
        return this.gameId;
    }


    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }


    public Rule getRule()
    {
        return this.rule;
    }


    public void setRule(Rule rule)
    {
        this.rule = rule;
    }


    public Player getPlayer1()
    {
        return this.player1;
    }


    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }


    public Player getPlayer2()
    {
        return this.player2;
    }


    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

}
