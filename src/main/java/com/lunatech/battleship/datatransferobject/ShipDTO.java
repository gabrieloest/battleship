package com.lunatech.battleship.datatransferobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lunatech.battleship.domainobject.Board;
import com.lunatech.battleship.domainobject.Cell;
import com.lunatech.battleship.domainobject.ShipType;
import com.lunatech.battleship.domainvalue.PositionType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipDTO
{

    @JsonIgnore
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
