package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.GameDTO;
import com.lunatech.battleship.domainobject.Game;

@Component
public class GameMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Game convertToEntity(GameDTO gameDTO)
    {
        return this.modelMapper.map(gameDTO, Game.class);
    }


    public GameDTO convertToDTO(Game game)
    {
        return this.modelMapper.map(game, GameDTO.class);
    }


    public List<GameDTO> convertGameDTOList(Collection<Game> games)
    {
        return games
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
