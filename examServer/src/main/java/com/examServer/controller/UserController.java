package com.examServer.controller;

import com.examServer.entity.Role;
import com.examServer.entity.User;
import com.examServer.entity.UserRole;
import com.examServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        user.setImage("default.png");
        Set<UserRole> userRoles = new HashSet<>();

        Role role = new Role();
        role.setRoleName("NORMAL");
        role.setId(45L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoles.add(userRole);
//        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        User user1 = this.userService.createUser(user, userRoles);
        return user1;
    }
}
