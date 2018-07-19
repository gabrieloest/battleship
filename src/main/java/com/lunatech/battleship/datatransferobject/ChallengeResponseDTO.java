package com.lunatech.battleship.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChallengeResponseDTO
{

    private String userId;
    private String fullName;
    private String gameId;
    private String starting;
    private String rules;


    public ChallengeResponseDTO()
    {}


    public String getUserId()
    {
        return this.userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId;
    }


    public String getFullName()
    {
        return this.fullName;
    }


    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }


    public String getGameId()
    {
        return this.gameId;
    }


    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }


    public String getStarting()
    {
        return this.starting;
    }


    public void setStarting(String starting)
    {
        this.starting = starting;
    }


    public String getRules()
    {
        return this.rules;
    }


    public void setRules(String rules)
    {
        this.rules = rules;
    }

}
