package com.blog.application.services.implementations;

import com.blog.application.configurations.AppConstants;
import com.blog.application.entities.Role;
import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.UserDto;
import com.blog.application.repositories.RoleRepository;
import com.blog.application.repositories.UserRepository;
import com.blog.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
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
        User foundedUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        foundedUser.setFirstName(user.getFirstName());
        foundedUser.setLastName(user.getLastName());
        foundedUser.setUserName(user.getUserName());
        foundedUser.setEmail(user.getEmail());
        foundedUser.setPassword(user.getPassword());
        foundedUser.setAbout(user.getAbout());

        User updatedUser = userRepository.save(foundedUser);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User foundedUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        return userToDto(foundedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User foundedUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        userRepository.delete(foundedUser);
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = this.roleRepository.findById(AppConstants.ADMIN_USER).orElseThrow(() -> new ResourceNotFoundException("role", "id", AppConstants.NORMAL_USER));
        user.getRoles().add(role);

        User newUser = this.userRepository.save(user);
        return this.modelMapper.map(newUser, UserDto.class);
    }
}
