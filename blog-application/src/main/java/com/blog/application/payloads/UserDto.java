package com.blog.application.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "First Name must be at least 4 characters")
    private String firstName;

    @NotEmpty
    @Size(min = 4, message = "Last Name must be at least 4 characters")
    private String lastName;

    @NotEmpty
    private String userName;

    @Email(message = "Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
    private String password;

    @NotNull
    private String about;

//    private Set<Role> roles = new HashSet<>();
}
