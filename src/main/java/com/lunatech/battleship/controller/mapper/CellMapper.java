package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.CellDTO;
import com.lunatech.battleship.domainobject.Cell;

@Component
public class CellMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Cell convertToEntity(CellDTO cellDTO)
    {
        return this.modelMapper.map(cellDTO, Cell.class);
    }


    public CellDTO convertToDTO(Cell cell)
    {
        return this.modelMapper.map(cell, CellDTO.class);
    }


    public List<CellDTO> convertCellDTOList(Collection<Cell> cells)
    {
        return cells
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
