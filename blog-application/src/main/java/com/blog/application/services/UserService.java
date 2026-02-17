package com.blog.application.services;

import com.blog.application.entities.User;
import com.blog.application.payloads.UserDto;

import java.util.List;

public interface UserService {
    // creating user
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

    UserDto registerNewUser(UserDto user);
}
