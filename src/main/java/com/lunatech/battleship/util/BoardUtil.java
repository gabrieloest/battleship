package com.lunatech.battleship.util;

import org.springframework.stereotype.Component;

import com.lunatech.battleship.domainobject.Board;

@Component
public class BoardUtil
{

    public String[] boardToArray(Board board)
    {

        String[] boardArray = new String[board.getWidth()];

        return boardArray;
    }
}
