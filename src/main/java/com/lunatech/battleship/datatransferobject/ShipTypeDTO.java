package com.lunatech.battleship.datatransferobject;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.lunatech.battleship.domainobject.Cell;

@Entity
public class ShipTypeDTO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

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


    public ZonedDateTime getDateCreated()
    {
        return this.dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
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
