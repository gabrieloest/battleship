package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Board;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BoardServiceImpl.class);

    private BoardRepository boardRepository;


    public BoardServiceImpl(final BoardRepository boardRepository)
    {
        this.boardRepository = boardRepository;
    }


    @Override
    public Board create(Board board) throws ConstraintsViolationException
    {
        return saveBoard(board);
    }


    @Override
    @Transactional
    public Board update(Board board) throws EntityNotFoundException, ConstraintsViolationException
    {
        findBoardById(board.getId());
        return saveBoard(board);
    }


    @Override
    @Transactional
    public void delete(Long boardId) throws EntityNotFoundException
    {
        this.boardRepository.deleteById(boardId);
    }


    @Override
    public Board find(Long boardId) throws EntityNotFoundException
    {
        return findBoardById(boardId);
    }


    @Override
    public Collection<Board> findAll()
    {
        return StreamSupport.stream(this.boardRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Board saveBoard(Board board) throws ConstraintsViolationException
    {
        Board savedBoard;
        try
        {
            savedBoard = this.boardRepository.save(board);
        }
        catch (DataIntegrityViolationException e)
        {
            BoardServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedBoard;
    }


    private Board findBoardById(Long boardId) throws EntityNotFoundException
    {
        return this.boardRepository.findById(boardId).orElseThrow(() -> new EntityNotFoundException("Could not find Board entity with id: " + boardId));
    }

}
