package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Player;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private PlayerRepository playerRepository;


    public PlayerServiceImpl(final PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }


    @Override
    public Player create(Player player) throws ConstraintsViolationException
    {
        return savePlayer(player);
    }


    @Override
    @Transactional
    public Player update(Player player) throws EntityNotFoundException, ConstraintsViolationException
    {
        findPlayerById(player.getId());
        return savePlayer(player);
    }


    @Override
    @Transactional
    public void delete(Long playerId) throws EntityNotFoundException
    {
        this.playerRepository.deleteById(playerId);
    }


    @Override
    public Player find(Long playerId) throws EntityNotFoundException
    {
        return findPlayerById(playerId);
    }


    @Override
    public Collection<Player> findAll()
    {
        return StreamSupport.stream(this.playerRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Player savePlayer(Player player) throws ConstraintsViolationException
    {
        Player savedPlayer;
        try
        {
            savedPlayer = this.playerRepository.save(player);
        }
        catch (DataIntegrityViolationException e)
        {
            PlayerServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedPlayer;
    }


    private Player findPlayerById(Long playerId) throws EntityNotFoundException
    {
        return this.playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Could not find Player entity with id: " + playerId));
    }

}
