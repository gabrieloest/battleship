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

import com.lunatech.battleship.controller.mapper.ShipMapper;
import com.lunatech.battleship.datatransferobject.ShipDTO;
import com.lunatech.battleship.domainobject.Ship;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.ShipService;

/**
 * All operations with a ship will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("ship")
public class ShipController
{

    private ShipService shipService;
    private ShipMapper shipMapper;


    @Autowired
    public ShipController(final ShipService shipService, final ShipMapper shipMapper)
    {
        this.shipService = shipService;
        this.shipMapper = shipMapper;
    }


    @GetMapping("/{shipId}")
    public ResponseEntity<?> getShip(@Valid @PathVariable long shipId)
    {

        try
        {
            return new ResponseEntity<>(this.shipMapper.convertToDTO(this.shipService.find(shipId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createShip(@Valid @RequestBody ShipDTO shipDTO)
    {

        Ship ship = this.shipMapper.convertToEntity(shipDTO);
        try
        {
            return new ResponseEntity<>(this.shipMapper.convertToDTO(this.shipService.create(ship)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{shipId}")
    public ResponseEntity<?> deleteShip(@Valid @PathVariable long shipId)
    {

        try
        {
            this.shipService.delete(shipId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{shipId}")
    public ResponseEntity<?> updateShip(@Valid @RequestBody ShipDTO shipDTO)
    {

        Ship ship = this.shipMapper.convertToEntity(shipDTO);
        try
        {
            return new ResponseEntity<>(this.shipService.update(ship), HttpStatus.OK);
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
    public ResponseEntity<?> getAllShips()
    {
        return ResponseEntity.ok(this.shipMapper.convertShipDTOList(this.shipService.findAll()));
    }
}
