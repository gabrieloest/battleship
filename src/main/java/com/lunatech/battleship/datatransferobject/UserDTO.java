package com.lunatech.battleship.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO
{

    @JsonIgnore
    private Long id;

    private String userId;


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

}
