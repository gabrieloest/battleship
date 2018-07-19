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

import com.lunatech.battleship.controller.mapper.ShipTypeMapper;
import com.lunatech.battleship.datatransferobject.ShipTypeDTO;
import com.lunatech.battleship.domainobject.ShipType;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.ShipTypeService;

/**
 * All operations with a shipType will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("shipType")
public class ShipTypeController
{

    private ShipTypeService shipTypeService;
    private ShipTypeMapper shipTypeMapper;


    @Autowired
    public ShipTypeController(final ShipTypeService shipTypeService, final ShipTypeMapper shipTypeMapper)
    {
        this.shipTypeService = shipTypeService;
        this.shipTypeMapper = shipTypeMapper;
    }


    @GetMapping("/{shipTypeId}")
    public ResponseEntity<?> getShipType(@Valid @PathVariable long shipTypeId)
    {

        try
        {
            return new ResponseEntity<>(this.shipTypeMapper.convertToDTO(this.shipTypeService.find(shipTypeId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createShipType(@Valid @RequestBody ShipTypeDTO shipTypeDTO)
    {

        ShipType shipType = this.shipTypeMapper.convertToEntity(shipTypeDTO);
        try
        {
            return new ResponseEntity<>(this.shipTypeMapper.convertToDTO(this.shipTypeService.create(shipType)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{shipTypeId}")
    public ResponseEntity<?> deleteShipType(@Valid @PathVariable long shipTypeId)
    {

        try
        {
            this.shipTypeService.delete(shipTypeId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{shipTypeId}")
    public ResponseEntity<?> updateShipType(@Valid @RequestBody ShipTypeDTO shipTypeDTO)
    {

        ShipType shipType = this.shipTypeMapper.convertToEntity(shipTypeDTO);
        try
        {
            return new ResponseEntity<>(this.shipTypeService.update(shipType), HttpStatus.OK);
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
    public ResponseEntity<?> getAllShipTypes()
    {
        return ResponseEntity.ok(this.shipTypeMapper.convertShipTypeDTOList(this.shipTypeService.findAll()));
    }
}
