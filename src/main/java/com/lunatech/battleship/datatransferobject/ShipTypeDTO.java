package com.lunatech.battleship.datatransferobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lunatech.battleship.domainobject.Cell;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipTypeDTO
{

    @JsonIgnore
    private Long id;

    private String name;

    private Integer width;

    private Integer heigth;

    private List<Cell> cells;


    public Long getId()
    {
        return this.id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getName()
    {
        return this.name;
    }


    public void setName(String name)
    {
        this.name = name;
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


    public List<Cell> getCells()
    {
        return cells;
    }


    public void setCells(List<Cell> cells)
    {
        this.cells = cells;
    }

}
