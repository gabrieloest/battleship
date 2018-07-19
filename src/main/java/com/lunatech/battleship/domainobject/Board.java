package com.lunatech.battleship.domainobject;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Board
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private Integer width;

    @Column(nullable = false)
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


    public ZonedDateTime getDateCreated()
    {
        return this.dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
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
