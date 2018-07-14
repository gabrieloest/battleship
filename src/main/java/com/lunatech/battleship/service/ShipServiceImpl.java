package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Ship;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.ShipRepository;

@Service
public class ShipServiceImpl implements ShipService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShipServiceImpl.class);

    private ShipRepository shipRepository;


    public ShipServiceImpl(final ShipRepository shipRepository)
    {
        this.shipRepository = shipRepository;
    }


    @Override
    public Ship create(Ship ship) throws ConstraintsViolationException
    {
        return saveShip(ship);
    }


    @Override
    @Transactional
    public Ship update(Ship ship) throws EntityNotFoundException, ConstraintsViolationException
    {
        findShipById(ship.getId());
        return saveShip(ship);
    }


    @Override
    @Transactional
    public void delete(Long shipId) throws EntityNotFoundException
    {
        this.shipRepository.deleteById(shipId);
    }


    @Override
    public Ship find(Long shipId) throws EntityNotFoundException
    {
        return findShipById(shipId);
    }


    @Override
    public Collection<Ship> findAll()
    {
        return StreamSupport.stream(this.shipRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Ship saveShip(Ship ship) throws ConstraintsViolationException
    {
        Ship savedShip;
        try
        {
            savedShip = this.shipRepository.save(ship);
        }
        catch (DataIntegrityViolationException e)
        {
            ShipServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedShip;
    }


    private Ship findShipById(Long shipId) throws EntityNotFoundException
    {
        return this.shipRepository.findById(shipId).orElseThrow(() -> new EntityNotFoundException("Could not find Ship entity with id: " + shipId));
    }

}
