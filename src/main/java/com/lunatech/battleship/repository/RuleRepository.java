package com.lunatech.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import com.lunatech.battleship.domainobject.Rule;
import com.lunatech.battleship.domainvalue.RuleType;

public interface RuleRepository extends CrudRepository<Rule, Long>
{

    Rule findByRule(RuleType rule);

}
