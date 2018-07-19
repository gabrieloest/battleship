package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.Rule;
import com.lunatech.battleship.domainvalue.RuleType;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.RuleRepository;

@Service
public class RuleServiceImpl implements RuleService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RuleServiceImpl.class);

    private RuleRepository ruleRepository;


    public RuleServiceImpl(final RuleRepository ruleRepository)
    {
        this.ruleRepository = ruleRepository;
    }


    @Override
    public Rule create(Rule rule) throws ConstraintsViolationException
    {
        return saveRule(rule);
    }


    @Override
    @Transactional
    public Rule update(Rule rule) throws EntityNotFoundException, ConstraintsViolationException
    {
        findRuleById(rule.getId());
        return saveRule(rule);
    }


    @Override
    @Transactional
    public void delete(Long ruleId) throws EntityNotFoundException
    {
        this.ruleRepository.deleteById(ruleId);
    }


    @Override
    public Rule find(Long ruleId) throws EntityNotFoundException
    {
        return findRuleById(ruleId);
    }


    @Override
    public Collection<Rule> findAll()
    {
        return StreamSupport.stream(this.ruleRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private Rule saveRule(Rule rule) throws ConstraintsViolationException
    {
        Rule savedRule;
        try
        {
            savedRule = this.ruleRepository.save(rule);
        }
        catch (DataIntegrityViolationException e)
        {
            RuleServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedRule;
    }


    private Rule findRuleById(Long ruleId) throws EntityNotFoundException
    {
        return this.ruleRepository.findById(ruleId).orElseThrow(() -> new EntityNotFoundException("Could not find Rule entity with id: " + ruleId));
    }


    @Override
    public Rule findByName(String rules)
    {
        return this.ruleRepository.findByRule(RuleType.valueOf(rules));
    }

}
