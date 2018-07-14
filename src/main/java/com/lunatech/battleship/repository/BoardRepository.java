package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Board;

public interface BoardRepository extends CrudRepository<Board, Long>
{

}
