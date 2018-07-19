package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Rule;

public interface RuleRepository extends CrudRepository<Rule, Long>
{

}
