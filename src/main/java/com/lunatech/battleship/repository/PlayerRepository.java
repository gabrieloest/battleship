package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>
{

}
