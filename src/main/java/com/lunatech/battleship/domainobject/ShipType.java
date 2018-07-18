package com.lunatech.battleship.domainobject;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShipType
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer width;

    @Column(nullable = false)
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

}
