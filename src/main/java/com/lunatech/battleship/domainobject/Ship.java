package com.lunatech.battleship.domainobject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.lunatech.battleship.domainvalue.PositionType;

@Entity
public class Ship
{

    @Id
    @GeneratedValue
    private Long id;

    private Board board;

    private ShipType shipType;

    private PositionType positionType;

    private List<Cell> cells;

    private Boolean alive;


    public Long getId()
    {
        return this.id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Board getBoard()
    {
        return this.board;
    }


    public void setBoard(Board board)
    {
        this.board = board;
    }


    public ShipType getShipType()
    {
        return this.shipType;
    }


    public void setShipType(ShipType shipType)
    {
        this.shipType = shipType;
    }


    public PositionType getPositionType()
    {
        return this.positionType;
    }


    public void setPositionType(PositionType positionType)
    {
        this.positionType = positionType;
    }


    public List<Cell> getCells()
    {
        return this.cells;
    }


    public void setCells(List<Cell> cells)
    {
        this.cells = cells;
    }


    public Boolean getAlive()
    {
        return this.alive;
    }


    public void setAlive(Boolean alive)
    {
        this.alive = alive;
    }

}
