package com.lunatech.battleship.datatransferobject;

public class GameStatusDTO
{

    private GameStatus game;
    private UserStatus self;
    private UserStatus opponent;


    public GameStatus getGame()
    {
        return this.game;
    }


    public void setGame(GameStatus game)
    {
        this.game = game;
    }


    public UserStatus getSelf()
    {
        return this.self;
    }


    public void setSelf(UserStatus self)
    {
        this.self = self;
    }


    public UserStatus getOpponent()
    {
        return this.opponent;
    }


    public void setOpponent(UserStatus opponent)
    {
        this.opponent = opponent;
    }
}
