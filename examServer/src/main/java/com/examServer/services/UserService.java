package com.examServer.services;

import com.examServer.entity.User;
import com.examServer.entity.UserRole;

import java.util.Set;

public interface UserService {

    // creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
