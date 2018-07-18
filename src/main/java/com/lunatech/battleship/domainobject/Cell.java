package com.lunatech.battleship.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Cell
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "The X value must be specified!")
    private Integer x;

    @Column(nullable = false)
    @NotNull(message = "The Y value must be specified!")
    private Integer y;

    @Column(nullable = false)
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
