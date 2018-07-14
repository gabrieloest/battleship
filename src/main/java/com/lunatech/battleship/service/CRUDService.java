package com.lunatech.battleship.service;

import java.util.Collection;

import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;

public interface CRUDService<T>
{
    T create(T t) throws ConstraintsViolationException;


    T update(T t) throws EntityNotFoundException, ConstraintsViolationException;


    void delete(Long id) throws EntityNotFoundException;


    T find(Long id) throws EntityNotFoundException;


    Collection<T> findAll();
}
