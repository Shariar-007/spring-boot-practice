package com.blog.application.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDao {
    private Integer postId;
    @NotEmpty
    private String postTitle;
    @NotEmpty
    @Size(min = 20, message = "content must be min of 20 characters !!")
    private String postContent;
    private Boolean isLive;
    private String image;
    private Date createdDate;
    private CategoryDao category;
    private UserDto user;
}
