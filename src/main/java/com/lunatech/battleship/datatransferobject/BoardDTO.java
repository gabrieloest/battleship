package com.lunatech.battleship.datatransferobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lunatech.battleship.domainobject.Cell;
import com.lunatech.battleship.domainobject.Player;
import com.lunatech.battleship.domainobject.Ship;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDTO
{

    @JsonIgnore
    private Long id;

    private Integer width;

    private Integer heigth;

    private Player player;

    private List<Cell> cells;

    private List<Ship> ships;


    public Long getId()
    {
        return this.id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Integer getWidth()
    {
        return this.width;
    }


    public void setWidth(Integer width)
    {
        this.width = width;
    }


    public Integer getHeigth()
    {
        return this.heigth;
    }


    public void setHeigth(Integer heigth)
    {
        this.heigth = heigth;
    }


    public Player getPlayer()
    {
        return this.player;
    }


    public void setPlayer(Player player)
    {
        this.player = player;
    }


    public List<Cell> getCells()
    {
        return this.cells;
    }


    public void setCells(List<Cell> cells)
    {
        this.cells = cells;
    }


    public List<Ship> getShips()
    {
        return this.ships;
    }


    public void setShips(List<Ship> ships)
    {
        this.ships = ships;
    }

}
