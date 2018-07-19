package com.lunatech.battleship.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.GameStatus;
import com.lunatech.battleship.datatransferobject.GameStatusDTO;
import com.lunatech.battleship.datatransferobject.UserStatus;
import com.lunatech.battleship.domainobject.Game;
import com.lunatech.battleship.util.BoardUtil;

@Component
public class GameStatusMapper
{
    @Autowired
    private BoardUtil boardUtil;


    public GameStatusDTO convertToDTO(Game game)
    {
        GameStatusDTO gameStatusDTO = new GameStatusDTO();

        GameStatus gameStatus = new GameStatus();
        gameStatus.setOwner(game.getStatusOwner());
        gameStatus.setStatus(game.getGameStatusType().description());

        gameStatusDTO.setGame(gameStatus);

        UserStatus self = new UserStatus();
        self.setUserId(game.getPlayer1().getUser().getUserId());
        self.setBoard(this.boardUtil.boardToArray(game.getPlayer1().getBoard()));

        gameStatusDTO.setSelf(self);

        UserStatus opponent = new UserStatus();
        opponent.setUserId(game.getPlayer2().getUser().getUserId());
        opponent.setBoard(this.boardUtil.boardToArray(game.getPlayer2().getBoard()));

        gameStatusDTO.setOpponent(opponent);

        return gameStatusDTO;
    }

}
