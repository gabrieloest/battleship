package com.lunatech.battleship.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lunatech.battleship.datatransferobject.UserDTO;
import com.lunatech.battleship.domainobject.User;

@Component
public class UserMapper
{
    @Autowired
    private ModelMapper modelMapper;


    public User convertToEntity(UserDTO userDTO)
    {
        return this.modelMapper.map(userDTO, User.class);
    }


    public UserDTO convertToDTO(User user)
    {
        return this.modelMapper.map(user, UserDTO.class);
    }


    public List<UserDTO> convertUserDTOList(Collection<User> users)
    {
        return users
            .stream()
            .map(it -> convertToDTO(it))
            .collect(Collectors.toList());
    }
}
