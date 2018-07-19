package com.lunatech.battleship.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChallengeRequestDTO
{

    private String userId;
    private String fullName;
    private String protocol;
    private String rules;


    public ChallengeRequestDTO()
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


    public String getProtocol()
    {
        return this.protocol;
    }


    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
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
