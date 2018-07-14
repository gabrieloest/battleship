package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Cell;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.CellRepository;

@Service
public class CellServiceImpl implements CellService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CellServiceImpl.class);

    private CellRepository cellRepository;


    public CellServiceImpl(final CellRepository cellRepository)
    {
        this.cellRepository = cellRepository;
    }


    @Override
    public Cell create(Cell cell) throws ConstraintsViolationException
    {
        return saveCell(cell);
    }


    @Override
    @Transactional
    public Cell update(Cell cell) throws EntityNotFoundException, ConstraintsViolationException
    {
        findCellById(cell.getId());
        return saveCell(cell);
    }


    @Override
    @Transactional
    public void delete(Long cellId) throws EntityNotFoundException
    {
        this.cellRepository.deleteById(cellId);
    }


    @Override
    public Cell find(Long cellId) throws EntityNotFoundException
    {
        return findCellById(cellId);
    }


    @Override
    public Collection<Cell> findAll()
    {
        return StreamSupport.stream(this.cellRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Cell saveCell(Cell cell) throws ConstraintsViolationException
    {
        Cell savedCell;
        try
        {
            savedCell = this.cellRepository.save(cell);
        }
        catch (DataIntegrityViolationException e)
        {
            CellServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedCell;
    }


    private Cell findCellById(Long cellId) throws EntityNotFoundException
    {
        return this.cellRepository.findById(cellId).orElseThrow(() -> new EntityNotFoundException("Could not find Cell entity with id: " + cellId));
    }

}
