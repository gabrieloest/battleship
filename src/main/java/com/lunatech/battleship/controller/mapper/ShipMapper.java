package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.ShipDTO;
import com.lunatech.battleship.domainobject.Ship;

@Component
public class ShipMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Ship convertToEntity(ShipDTO shipDTO)
    {
        return this.modelMapper.map(shipDTO, Ship.class);
    }


    public ShipDTO convertToDTO(Ship ship)
    {
        return this.modelMapper.map(ship, ShipDTO.class);
    }


    public List<ShipDTO> convertShipDTOList(Collection<Ship> ships)
    {
        return ships
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
