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
}
