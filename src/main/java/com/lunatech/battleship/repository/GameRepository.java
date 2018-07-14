package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Game;

public interface GameRepository extends CrudRepository<Game, Long>
{

}
