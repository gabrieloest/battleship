package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Position;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.PositionRepository;

@Service
public class PositionServiceImpl implements PositionService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PositionServiceImpl.class);

    private PositionRepository positionRepository;


    public PositionServiceImpl(final PositionRepository positionRepository)
    {
        this.positionRepository = positionRepository;
    }


    @Override
    public Position create(Position position) throws ConstraintsViolationException
    {
        return savePosition(position);
    }


    @Override
    @Transactional
    public Position update(Position position) throws EntityNotFoundException, ConstraintsViolationException
    {
        findPositionById(position.getId());
        return savePosition(position);
    }


    @Override
    @Transactional
    public void delete(Long positionId) throws EntityNotFoundException
    {
        this.positionRepository.deleteById(positionId);
    }


    @Override
    public Position find(Long positionId) throws EntityNotFoundException
    {
        return findPositionById(positionId);
    }


    @Override
    public Collection<Position> findAll()
    {
        return StreamSupport.stream(this.positionRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Position savePosition(Position position) throws ConstraintsViolationException
    {
        Position savedPosition;
        try
        {
            savedPosition = this.positionRepository.save(position);
        }
        catch (DataIntegrityViolationException e)
        {
            PositionServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedPosition;
    }


    private Position findPositionById(Long positionId) throws EntityNotFoundException
    {
        return this.positionRepository.findById(positionId).orElseThrow(() -> new EntityNotFoundException("Could not find Position entity with id: " + positionId));
    }

}
