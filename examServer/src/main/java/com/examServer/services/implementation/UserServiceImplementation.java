package com.examServer.services.implementation;

import com.examServer.entity.User;
import com.examServer.entity.UserRole;
import com.examServer.repository.RoleRepository;
import com.examServer.repository.UserRepository;
import com.examServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findUserByUserName(user.getUserName());

        if (local != null) {
            System.out.println("User is already there !!");
            throw new Exception("User is already there !!");
        } else {
            // user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }
}
