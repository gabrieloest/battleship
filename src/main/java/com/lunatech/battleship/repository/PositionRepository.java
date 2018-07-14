package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Position;

public interface PositionRepository extends CrudRepository<Position, Long>
{

}
