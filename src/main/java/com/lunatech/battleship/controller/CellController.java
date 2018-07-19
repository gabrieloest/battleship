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

import com.lunatech.battleship.controller.mapper.CellMapper;
import com.lunatech.battleship.datatransferobject.CellDTO;
import com.lunatech.battleship.domainobject.Cell;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.CellService;

/**
 * All operations with a cell will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("cell")
public class CellController
{

    private CellService cellService;
    private CellMapper cellMapper;


    @Autowired
    public CellController(final CellService cellService, final CellMapper cellMapper)
    {
        this.cellService = cellService;
        this.cellMapper = cellMapper;
    }


    @GetMapping("/{cellId}")
    public ResponseEntity<?> getCell(@Valid @PathVariable long cellId)
    {

        try
        {
            return new ResponseEntity<>(this.cellMapper.convertToDTO(this.cellService.find(cellId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCell(@Valid @RequestBody CellDTO cellDTO)
    {

        Cell cell = this.cellMapper.convertToEntity(cellDTO);
        try
        {
            return new ResponseEntity<>(this.cellMapper.convertToDTO(this.cellService.create(cell)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{cellId}")
    public ResponseEntity<?> deleteCell(@Valid @PathVariable long cellId)
    {

        try
        {
            this.cellService.delete(cellId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{cellId}")
    public ResponseEntity<?> updateCell(@Valid @RequestBody CellDTO cellDTO)
    {

        Cell cell = this.cellMapper.convertToEntity(cellDTO);
        try
        {
            return new ResponseEntity<>(this.cellService.update(cell), HttpStatus.OK);
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
    public ResponseEntity<?> getAllCells()
    {
        return ResponseEntity.ok(this.cellMapper.convertCellDTOList(this.cellService.findAll()));
    }
}
