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

import com.lunatech.battleship.controller.mapper.BoardMapper;
import com.lunatech.battleship.datatransferobject.BoardDTO;
import com.lunatech.battleship.domainobject.Board;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.service.BoardService;

/**
 * All operations with a board will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("board")
public class BoardController
{

    private BoardService boardService;
    private BoardMapper boardMapper;


    @Autowired
    public BoardController(final BoardService boardService, final BoardMapper boardMapper)
    {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }


    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoard(@Valid @PathVariable long boardId)
    {

        try
        {
            return new ResponseEntity<>(this.boardMapper.convertToDTO(this.boardService.find(boardId)), HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBoard(@Valid @RequestBody BoardDTO boardDTO)
    {

        Board board = this.boardMapper.convertToEntity(boardDTO);
        try
        {
            return new ResponseEntity<>(this.boardMapper.convertToDTO(this.boardService.create(board)), HttpStatus.CREATED);
        }
        catch (ConstraintsViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@Valid @PathVariable long boardId)
    {

        try
        {
            this.boardService.delete(boardId);
            return ResponseEntity
                .noContent()
                .build();
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@Valid @RequestBody BoardDTO boardDTO)
    {

        Board board = this.boardMapper.convertToEntity(boardDTO);
        try
        {
            return new ResponseEntity<>(this.boardService.update(board), HttpStatus.OK);
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
    public ResponseEntity<?> getAllBoards()
    {
        return ResponseEntity.ok(this.boardMapper.convertBoardDTOList(this.boardService.findAll()));
    }
}
