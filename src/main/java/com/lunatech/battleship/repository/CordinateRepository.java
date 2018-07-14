package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Cordinate;

public interface CordinateRepository extends CrudRepository<Cordinate, Long>
{

}
