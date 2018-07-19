package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.CordinateDTO;
import com.lunatech.battleship.domainobject.Cordinate;

@Component
public class CordinateMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Cordinate convertToEntity(CordinateDTO cordinateDTO)
    {
        return this.modelMapper.map(cordinateDTO, Cordinate.class);
    }


    public CordinateDTO convertToDTO(Cordinate cordinate)
    {
        return this.modelMapper.map(cordinate, CordinateDTO.class);
    }


    public List<CordinateDTO> convertCordinateDTOList(Collection<Cordinate> cordinates)
    {
        return cordinates
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
