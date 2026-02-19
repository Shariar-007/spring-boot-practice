package com.blog.application.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id;

    private String firstName;
    private String lastName;
    private String userName;

    private String email;

    private String password;

    private String about;

//    private Set<Role> roles = new HashSet<>();
}
