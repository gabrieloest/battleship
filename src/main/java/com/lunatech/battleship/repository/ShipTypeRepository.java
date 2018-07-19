package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.ShipType;

public interface ShipTypeRepository extends CrudRepository<ShipType, Long>
{

}
