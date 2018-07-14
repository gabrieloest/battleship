package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Cell;

public interface CellRepository extends CrudRepository<Cell, Long>
{

}
