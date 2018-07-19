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

import com.lunatech.battleship.controller.mapper.GameMapper;
import com.lunatech.battleship.datatransferobject.GameDTO;
import com.lunatech.battleship.domainobject.Game;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.GameService;

/**
 * All operations with a game will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("game")
public class GameController
{

    private GameService gameService;
    private GameMapper gameMapper;


    @Autowired
    public GameController(final GameService gameService, final GameMapper gameMapper)
    {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }


    @GetMapping("/{gameId}")
    public ResponseEntity<?> getGame(@Valid @PathVariable long gameId)
    {

        try
        {
            return new ResponseEntity<>(this.gameMapper.convertToDTO(this.gameService.find(gameId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createGame(@Valid @RequestBody GameDTO gameDTO)
    {

        Game game = this.gameMapper.convertToEntity(gameDTO);
        try
        {
            return new ResponseEntity<>(this.gameMapper.convertToDTO(this.gameService.create(game)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> deleteGame(@Valid @PathVariable long gameId)
    {

        try
        {
            this.gameService.delete(gameId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{gameId}")
    public ResponseEntity<?> updateGame(@Valid @RequestBody GameDTO gameDTO)
    {

        Game game = this.gameMapper.convertToEntity(gameDTO);
        try
        {
            return new ResponseEntity<>(this.gameService.update(game), HttpStatus.OK);
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
    public ResponseEntity<?> getAllGames()
    {
        return ResponseEntity.ok(this.gameMapper.convertGameDTOList(this.gameService.findAll()));
    }
}
