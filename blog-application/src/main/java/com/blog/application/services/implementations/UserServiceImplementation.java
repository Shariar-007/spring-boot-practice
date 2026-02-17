package com.blog.application.services.implementations;

import com.blog.application.entities.User;
import com.blog.application.payloads.UserDto;
import com.blog.application.repositories.UserRepository;
import com.blog.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    public UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public UserDto registerNewUser(UserDto user) {
        return null;
    }
}
