package com.lunatech.battleship.domainobject;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.lunatech.battleship.domainvalue.GameStatusType;

@Entity
public class Game
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

    private GameStatusType gameStatusType;

    private String statusOwner;


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


    public GameStatusType getGameStatusType()
    {
        return this.gameStatusType;
    }


    public void setGameStatusType(GameStatusType gameStatusType)
    {
        this.gameStatusType = gameStatusType;
    }


    public String getStatusOwner()
    {
        return this.statusOwner;
    }


    public void setStatusOwner(String statusOwner)
    {
        this.statusOwner = statusOwner;
    }


    public Optional<Player> getPlayerByUserId(String userId)
    {
        if (this.player1.getUser().getUserId().equals(userId))
        {
            return Optional.ofNullable(this.player1);
        }

        return Optional.ofNullable(this.player2);
    }

}
