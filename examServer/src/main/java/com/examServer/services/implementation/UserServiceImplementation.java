package com.examServer.services.implementation;

import com.examServer.entity.User;
import com.examServer.entity.UserRole;
import com.examServer.exceptionHandler.UserFoundException;
import com.examServer.exceptionHandler.UserNotFoundException;
import com.examServer.repository.RoleRepository;
import com.examServer.repository.UserRepository;
import com.examServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        User local = this.userRepository.findUserByUserName(user.getUsername());

        if (local != null) {
            System.out.println("User is already there !!");
            throw new UserFoundException("User is already there !!");
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

    @Override
    public User getUser(String userName) throws Exception {
        return this.userRepository.findUserByUserName(userName);
    }

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public User getUserById(Long userId) throws Exception {
        return this.userRepository.findById(userId).orElseThrow(() -> new Exception("user not Found"));
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        this.userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user, Long userId) throws Exception {
        // userName and password can not be updatable
        User foundedUser = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User is not present !!"));
        foundedUser.setFirstName(user.getFirstName());
        foundedUser.setLastName(user.getLastName());
        foundedUser.setEmail(user.getEmail());
        foundedUser.setAbout(user.getAbout());
        foundedUser.setImage(user.getImage());
        foundedUser.setPhone(user.getPhone());
        foundedUser.setEnable(user.getEnable());

        User updatedUser = this.userRepository.save(foundedUser);
        return updatedUser;
    }
}
