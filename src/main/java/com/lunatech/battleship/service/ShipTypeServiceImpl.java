package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.ShipType;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.ShipTypeRepository;

@Service
public class ShipTypeServiceImpl implements ShipTypeService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShipTypeServiceImpl.class);

    private ShipTypeRepository shipTypeRepository;


    public ShipTypeServiceImpl(final ShipTypeRepository shipTypeRepository)
    {
        this.shipTypeRepository = shipTypeRepository;
    }


    @Override
    public ShipType create(ShipType shipType) throws ConstraintsViolationException
    {
        return saveShipType(shipType);
    }


    @Override
    @Transactional
    public ShipType update(ShipType shipType) throws EntityNotFoundException, ConstraintsViolationException
    {
        findShipTypeById(shipType.getId());
        return saveShipType(shipType);
    }


    @Override
    @Transactional
    public void delete(Long shipTypeId) throws EntityNotFoundException
    {
        this.shipTypeRepository.deleteById(shipTypeId);
    }


    @Override
    public ShipType find(Long shipTypeId) throws EntityNotFoundException
    {
        return findShipTypeById(shipTypeId);
    }


    @Override
    public Collection<ShipType> findAll()
    {
        return StreamSupport.stream(this.shipTypeRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private ShipType saveShipType(ShipType shipType) throws ConstraintsViolationException
    {
        ShipType savedShipType;
        try
        {
            savedShipType = this.shipTypeRepository.save(shipType);
        }
        catch (DataIntegrityViolationException e)
        {
            ShipTypeServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedShipType;
    }


    private ShipType findShipTypeById(Long shipTypeId) throws EntityNotFoundException
    {
        return this.shipTypeRepository.findById(shipTypeId).orElseThrow(() -> new EntityNotFoundException("Could not find ShipType entity with id: " + shipTypeId));
    }

}
