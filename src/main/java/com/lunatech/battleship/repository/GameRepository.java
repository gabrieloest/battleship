package com.lunatech.battleship.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Game;

public interface GameRepository extends CrudRepository<Game, Long>
{

    Optional<Game> findByGameId(String gameId);

}
