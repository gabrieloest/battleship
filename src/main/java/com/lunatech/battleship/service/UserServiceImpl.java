package com.lunatech.battleship.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunatech.battleship.domainobject.User;
import com.lunatech.battleship.exception.ConstraintsViolationException;
import com.lunatech.battleship.exception.EntityNotFoundException;
import com.lunatech.battleship.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;


    public UserServiceImpl(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) throws ConstraintsViolationException
    {
        return saveUser(user);
    }


    @Override
    @Transactional
    public User update(User user) throws EntityNotFoundException, ConstraintsViolationException
    {
        findUserById(user.getId());
        return saveUser(user);
    }


    @Override
    @Transactional
    public void delete(Long userId) throws EntityNotFoundException
    {
        this.userRepository.deleteById(userId);
    }


    @Override
    public User find(Long userId) throws EntityNotFoundException
    {
        return findUserById(userId);
    }


    @Override
    public Collection<User> findAll()
    {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }


    private User saveUser(User user) throws ConstraintsViolationException
    {
        User savedUser;
        try
        {
            savedUser = this.userRepository.save(user);
        }
        catch (DataIntegrityViolationException e)
        {
            UserServiceImpl.LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return savedUser;
    }


    private User findUserById(Long userId) throws EntityNotFoundException
    {
        return this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Could not find User entity with id: " + userId));
    }

}
