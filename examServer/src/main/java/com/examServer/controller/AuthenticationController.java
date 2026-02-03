package com.examServer.controller;

import com.examServer.entity.JwtRequest;
import com.examServer.entity.JwtResponse;
import com.examServer.entity.User;
import com.examServer.exceptionHandler.UserFoundException;
import com.examServer.jwtConfig.JwtTokenHelper;
import com.examServer.services.implementation.UserDetailServiceImplementation;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImplementation userDetailServiceImplementation;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    private void authenticate(String username, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("USER DISABLED" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    @Hidden
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.Operation(security = { }) // This removes the lock icon for this specific API
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            // Step 1: Authenticate the user
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UserFoundException e){
            e.printStackTrace();
            throw new UserFoundException("User not found");
        }
        ///////////// authenticate
        // Step 2: Load user details
        UserDetails userDetails = this.userDetailServiceImplementation.loadUserByUsername(jwtRequest.getUsername());
        System.out.println(userDetails);
        // Step 3: Generate the token
        String token = this.jwtTokenHelper.generateToken(userDetails);
        // Step 4: Return response
        return ResponseEntity.ok(new JwtResponse("Bearer " + token));
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) this.userDetailServiceImplementation.loadUserByUsername(principal.getName());
    }
}
