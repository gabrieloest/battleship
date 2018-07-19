package com.lunatech.battleship.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lunatech.battleship.domainobject.Board;
import com.lunatech.battleship.domainobject.Ship;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CellDTO
{

    @JsonIgnore
    private Long id;

    @NotNull(message = "The X value must be specified!")
    private Integer x;

    @NotNull(message = "The Y value must be specified!")
    private Integer y;

    private Boolean occupied = false;

    private Board board;

    private Ship ship;


    public Long getId()
    {
        return this.id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Integer getX()
    {
        return this.x;
    }


    public void setX(Integer x)
    {
        this.x = x;
    }


    public Integer getY()
    {
        return this.y;
    }


    public void setY(Integer y)
    {
        this.y = y;
    }


    public Boolean getOccupied()
    {
        return this.occupied;
    }


    public void setOccupied(Boolean occupied)
    {
        this.occupied = occupied;
    }


    public Board getBoard()
    {
        return this.board;
    }


    public void setBoard(Board board)
    {
        this.board = board;
    }


    public Ship getShip()
    {
        return this.ship;
    }


    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

}
