package com.lunatech.battleship.datatransferobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.lunatech.battleship.domainvalue.RuleType;

@Entity
public class RuleDTO
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuleType ruleType;

    @Column(nullable = false)
    private Boolean extraShotTurn = false;

    @Column(nullable = false)
    private Boolean extraShotPermanent = false;

    @NotNull(message = "The number of shots must be specified!")
    @Column(nullable = false)
    @Min(value = 1, message = "The minimum number of shots is 1")
    @Max(value = 10, message = "The maximum number of shots is 10")
    private Integer shots;


    public RuleDTO()
    {}


    public RuleDTO(RuleType ruleType, Boolean extraShotTurn, Boolean extraShotPermanent, Integer shots)
    {
        this.ruleType = ruleType;
        this.extraShotTurn = extraShotTurn;
        this.extraShotPermanent = extraShotPermanent;
        this.shots = shots;
    }


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


    public RuleType getRuleType()
    {
        return this.ruleType;
    }


    public void setRuleType(RuleType ruleType)
    {
        this.ruleType = ruleType;
    }


    public Boolean getExtraShotTurn()
    {
        return this.extraShotTurn;
    }


    public void setExtraShotTurn(Boolean extraShotTurn)
    {
        this.extraShotTurn = extraShotTurn;
    }


    public Boolean getExtraShotPermanent()
    {
        return this.extraShotPermanent;
    }


    public void setExtraShotPermanent(Boolean extraShotPermanent)
    {
        this.extraShotPermanent = extraShotPermanent;
    }


    public Integer getShots()
    {
        return this.shots;
    }


    public void setShots(Integer shots)
    {
        this.shots = shots;
    }

}
