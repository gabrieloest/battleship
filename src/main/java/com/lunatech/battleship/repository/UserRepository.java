package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.User;

public interface UserRepository extends CrudRepository<User, Long>
{

}
