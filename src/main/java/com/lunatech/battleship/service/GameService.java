package com.lunatech.battleship.service;

import com.lunatech.battleship.domainobject.Game;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;

public interface GameService extends CRUDService<Game>
{

    Game findByGameId(String gameId) throws EntityNotFoundException;


    void updateUserAutoPilot(String gameId, String userId) throws EntityNotFoundException, ConstraintsViolationException;
}
