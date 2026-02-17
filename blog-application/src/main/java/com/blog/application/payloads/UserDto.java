package com.blog.application.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    private String firstName;
    private String lastName;
    private String userName;

    private String email;

    private String password;

    @NotNull
    private String about;

//    private Set<Role> roles = new HashSet<>();
}
