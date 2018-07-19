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

import com.lunatech.battleship.controller.mapper.CordinateMapper;
import com.lunatech.battleship.datatransferobject.CordinateDTO;
import com.lunatech.battleship.domainobject.Cordinate;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.CordinateService;

/**
 * All operations with a cordinate will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("cordinate")
public class CordinateController
{

    private CordinateService cordinateService;
    private CordinateMapper cordinateMapper;


    @Autowired
    public CordinateController(final CordinateService cordinateService, final CordinateMapper cordinateMapper)
    {
        this.cordinateService = cordinateService;
        this.cordinateMapper = cordinateMapper;
    }


    @GetMapping("/{cordinateId}")
    public ResponseEntity<?> getCordinate(@Valid @PathVariable long cordinateId)
    {

        try
        {
            return new ResponseEntity<>(this.cordinateMapper.convertToDTO(this.cordinateService.find(cordinateId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCordinate(@Valid @RequestBody CordinateDTO cordinateDTO)
    {

        Cordinate cordinate = this.cordinateMapper.convertToEntity(cordinateDTO);
        try
        {
            return new ResponseEntity<>(this.cordinateMapper.convertToDTO(this.cordinateService.create(cordinate)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{cordinateId}")
    public ResponseEntity<?> deleteCordinate(@Valid @PathVariable long cordinateId)
    {

        try
        {
            this.cordinateService.delete(cordinateId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{cordinateId}")
    public ResponseEntity<?> updateCordinate(@Valid @RequestBody CordinateDTO cordinateDTO)
    {

        Cordinate cordinate = this.cordinateMapper.convertToEntity(cordinateDTO);
        try
        {
            return new ResponseEntity<>(this.cordinateService.update(cordinate), HttpStatus.OK);
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
    public ResponseEntity<?> getAllCordinates()
    {
        return ResponseEntity.ok(this.cordinateMapper.convertCordinateDTOList(this.cordinateService.findAll()));
    }
}
