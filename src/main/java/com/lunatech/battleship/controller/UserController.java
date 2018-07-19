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

import com.lunatech.battleship.controller.mapper.GameStatusMapper;
import com.lunatech.battleship.controller.mapper.UserMapper;
import com.lunatech.battleship.datatransferobject.UserDTO;
import com.lunatech.battleship.domainobject.User;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.security.IAuthenticationFacade;
import com.lunatech.battleship.service.GameService;
import com.lunatech.battleship.service.UserService;

/**
 * All operations with a user will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("user")
public class UserController
{

    private UserService userService;
    private GameService gameService;
    private UserMapper userMapper;
    private GameStatusMapper gameStatusMapper;
    private IAuthenticationFacade authenticationFacade;


    @Autowired
    public UserController(
        final UserService userService, final GameService gameService, final UserMapper userMapper, final GameStatusMapper gameStatusMapper,
        final IAuthenticationFacade authenticationFacade)
    {
        this.userService = userService;
        this.gameService = gameService;
        this.userMapper = userMapper;
        this.gameStatusMapper = gameStatusMapper;
        this.authenticationFacade = authenticationFacade;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@Valid @PathVariable long userId)
    {

        try
        {
            return new ResponseEntity<>(this.userMapper.convertToDTO(this.userService.find(userId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO)
    {

        User user = this.userMapper.convertToEntity(userDTO);
        try
        {
            return new ResponseEntity<>(this.userMapper.convertToDTO(this.userService.create(user)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable long userId)
    {

        try
        {
            this.userService.delete(userId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO)
    {

        User user = this.userMapper.convertToEntity(userDTO);
        try
        {
            return new ResponseEntity<>(this.userService.update(user), HttpStatus.OK);
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
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(this.userMapper.convertUserDTOList(this.userService.findAll()));
    }


    @GetMapping("/game/{gameId}")
    public ResponseEntity<?> getGameStatus(@Valid @PathVariable String gameId)
    {
        try
        {
            return ResponseEntity.ok(this.gameStatusMapper.convertToDTO(this.gameService.findByGameId(gameId)));
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/user/game/{gameId}/auto")
    public ResponseEntity<?> autoPilotUser(@Valid @PathVariable String gameId)
    {
        try
        {
            String userId = this.authenticationFacade.getAuthentication().getName();
            this.gameService.updateUserAutoPilot(gameId, userId);

            return ResponseEntity
                .noContent()
                .build();
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
}
