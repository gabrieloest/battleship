package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.PlayerDTO;
import com.lunatech.battleship.domainobject.Player;

@Component
public class PlayerMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Player convertToEntity(PlayerDTO playerDTO)
    {
        return this.modelMapper.map(playerDTO, Player.class);
    }


    public PlayerDTO convertToDTO(Player player)
    {
        return this.modelMapper.map(player, PlayerDTO.class);
    }


    public List<PlayerDTO> convertPlayerDTOList(Collection<Player> players)
    {
        return players
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
