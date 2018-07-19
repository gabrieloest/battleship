package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Game;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    private GameRepository gameRepository;


    public GameServiceImpl(final GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }


    @Override
    public Game create(Game game) throws ConstraintsViolationException
    {
        return saveGame(game);
    }


    @Override
    @Transactional
    public Game update(Game game) throws EntityNotFoundException, ConstraintsViolationException
    {
        findGameById(game.getId());
        return saveGame(game);
    }


    @Override
    @Transactional
    public void delete(Long gameId) throws EntityNotFoundException
    {
        this.gameRepository.deleteById(gameId);
    }


    @Override
    public Game find(Long gameId) throws EntityNotFoundException
    {
        return findGameById(gameId);
    }


    @Override
    public Collection<Game> findAll()
    {
        return StreamSupport.stream(this.gameRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Game saveGame(Game game) throws ConstraintsViolationException
    {
        Game savedGame;
        try
        {
            savedGame = this.gameRepository.save(game);
        }
        catch (DataIntegrityViolationException e)
        {
            GameServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedGame;
    }


    private Game findGameById(Long gameId) throws EntityNotFoundException
    {
        return this.gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Could not find Game entity with id: " + gameId));
    }


    @Override
    public Game findByGameId(String gameId) throws EntityNotFoundException
    {
        return this.gameRepository.findByGameId(gameId).orElseThrow(() -> new EntityNotFoundException("Could not find Game entity with gameId: " + gameId));
    }


    @Override
    public void updateUserAutoPilot(String gameId, String userId) throws EntityNotFoundException, ConstraintsViolationException
    {
        Game game = findByGameId(gameId);
        game.getPlayerByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Could not find authenticated User in this game: " + gameId)).setAutoPilot(true);;
        update(game);

    }

}
