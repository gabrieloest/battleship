package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.BoardDTO;
import com.lunatech.battleship.domainobject.Board;

@Component
public class BoardMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Board convertToEntity(BoardDTO boardDTO)
    {
        return this.modelMapper.map(boardDTO, Board.class);
    }


    public BoardDTO convertToDTO(Board board)
    {
        return this.modelMapper.map(board, BoardDTO.class);
    }


    public List<BoardDTO> convertBoardDTOList(Collection<Board> boards)
    {
        return boards
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
