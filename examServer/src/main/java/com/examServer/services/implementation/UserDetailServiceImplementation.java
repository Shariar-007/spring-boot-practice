package com.examServer.services.implementation;

import com.examServer.entity.User;
import com.examServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // loading user from database by username
        User user = userRepository.findUserByUserName(username);
        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("No User found !!");
        }
        return user;
    }
}
