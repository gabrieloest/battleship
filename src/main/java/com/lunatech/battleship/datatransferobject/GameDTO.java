package com.lunatech.battleship.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lunatech.battleship.domainobject.Player;
import com.lunatech.battleship.domainobject.Rule;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDTO
{

    @JsonIgnore
    private Long id;

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
