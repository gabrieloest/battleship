package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Ship;

public interface ShipRepository extends CrudRepository<Ship, Long>
{

}
