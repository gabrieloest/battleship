package com.lunatech.battleship.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunatech.battleship.controller.mapper.RuleMapper;
import com.lunatech.battleship.datatransferobject.RuleDTO;
import com.lunatech.battleship.domainobject.Rule;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.RuleService;

/**
 * All operations with a rule will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("rule")
public class RuleController
{

    private RuleService ruleService;
    private RuleMapper ruleMapper;


    @Autowired
    public RuleController(final RuleService ruleService, final RuleMapper ruleMapper)
    {
        this.ruleService = ruleService;
        this.ruleMapper = ruleMapper;
    }


    @GetMapping("/{ruleId}")
    public ResponseEntity<?> getRule(@Valid @PathVariable long ruleId)
    {

        try
        {
            return new ResponseEntity<>(this.ruleMapper.convertToDTO(this.ruleService.find(ruleId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createRule(@Valid @RequestBody RuleDTO ruleDTO)
    {

        Rule rule = this.ruleMapper.convertToEntity(ruleDTO);
        try
        {
            return new ResponseEntity<>(this.ruleMapper.convertToDTO(this.ruleService.create(rule)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{ruleId}")
    public ResponseEntity<?> deleteRule(@Valid @PathVariable long ruleId)
    {

        try
        {
            this.ruleService.delete(ruleId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{ruleId}")
    public ResponseEntity<?> updateRule(@Valid @RequestBody RuleDTO ruleDTO)
    {

        Rule rule = this.ruleMapper.convertToEntity(ruleDTO);
        try
        {
            return new ResponseEntity<>(this.ruleService.update(rule), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllRules()
    {
        return ResponseEntity.ok(this.ruleMapper.convertRuleDTOList(this.ruleService.findAll()));
    }
}
