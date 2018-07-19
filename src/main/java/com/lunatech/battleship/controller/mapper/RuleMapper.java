package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.RuleDTO;
import com.lunatech.battleship.domainobject.Rule;

@Component
public class RuleMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public Rule convertToEntity(RuleDTO ruleDTO)
    {
        return this.modelMapper.map(ruleDTO, Rule.class);
    }


    public RuleDTO convertToDTO(Rule rule)
    {
        return this.modelMapper.map(rule, RuleDTO.class);
    }


    public List<RuleDTO> convertRuleDTOList(Collection<Rule> rules)
    {
        return rules
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
