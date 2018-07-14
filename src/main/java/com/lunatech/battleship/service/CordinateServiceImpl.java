package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Cordinate;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.CordinateRepository;

@Service
public class CordinateServiceImpl implements CordinateService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CordinateServiceImpl.class);

    private CordinateRepository cordinateRepository;


    public CordinateServiceImpl(final CordinateRepository cordinateRepository)
    {
        this.cordinateRepository = cordinateRepository;
    }


    @Override
    public Cordinate create(Cordinate cordinate) throws ConstraintsViolationException
    {
        return saveCordinate(cordinate);
    }


    @Override
    @Transactional
    public Cordinate update(Cordinate cordinate) throws EntityNotFoundException, ConstraintsViolationException
    {
        findCordinateById(cordinate.getId());
        return saveCordinate(cordinate);
    }


    @Override
    @Transactional
    public void delete(Long cordinateId) throws EntityNotFoundException
    {
        this.cordinateRepository.deleteById(cordinateId);
    }


    @Override
    public Cordinate find(Long cordinateId) throws EntityNotFoundException
    {
        return findCordinateById(cordinateId);
    }


    @Override
    public Collection<Cordinate> findAll()
    {
        return StreamSupport.stream(this.cordinateRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Cordinate saveCordinate(Cordinate cordinate) throws ConstraintsViolationException
    {
        Cordinate savedCordinate;
        try
        {
            savedCordinate = this.cordinateRepository.save(cordinate);
        }
        catch (DataIntegrityViolationException e)
        {
            CordinateServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedCordinate;
    }


    private Cordinate findCordinateById(Long cordinateId) throws EntityNotFoundException
    {
        return this.cordinateRepository.findById(cordinateId).orElseThrow(() -> new EntityNotFoundException("Could not find Cordinate entity with id: " + cordinateId));
    }

}
