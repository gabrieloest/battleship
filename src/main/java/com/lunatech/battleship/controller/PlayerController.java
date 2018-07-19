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

import com.lunatech.battleship.controller.mapper.PlayerMapper;
import com.lunatech.battleship.datatransferobject.PlayerDTO;
import com.lunatech.battleship.domainobject.Player;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.PlayerService;

/**
 * All operations with a player will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("player")
public class PlayerController
{

    private PlayerService playerService;
    private PlayerMapper playerMapper;


    @Autowired
    public PlayerController(final PlayerService playerService, final PlayerMapper playerMapper)
    {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }


    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayer(@Valid @PathVariable long playerId)
    {

        try
        {
            return new ResponseEntity<>(this.playerMapper.convertToDTO(this.playerService.find(playerId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPlayer(@Valid @RequestBody PlayerDTO playerDTO)
    {

        Player player = this.playerMapper.convertToEntity(playerDTO);
        try
        {
            return new ResponseEntity<>(this.playerMapper.convertToDTO(this.playerService.create(player)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@Valid @PathVariable long playerId)
    {

        try
        {
            this.playerService.delete(playerId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(@Valid @RequestBody PlayerDTO playerDTO)
    {

        Player player = this.playerMapper.convertToEntity(playerDTO);
        try
        {
            return new ResponseEntity<>(this.playerService.update(player), HttpStatus.OK);
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
    public ResponseEntity<?> getAllPlayers()
    {
        return ResponseEntity.ok(this.playerMapper.convertPlayerDTOList(this.playerService.findAll()));
    }
}
