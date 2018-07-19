package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.ShipTypeDTO;
import com.lunatech.battleship.domainobject.ShipType;

@Component
public class ShipTypeMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public ShipType convertToEntity(ShipTypeDTO shipTypeDTO)
    {
        return this.modelMapper.map(shipTypeDTO, ShipType.class);
    }


    public ShipTypeDTO convertToDTO(ShipType shipType)
    {
        return this.modelMapper.map(shipType, ShipTypeDTO.class);
    }


    public List<ShipTypeDTO> convertShipTypeDTOList(Collection<ShipType> shipTypes)
    {
        return shipTypes
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
