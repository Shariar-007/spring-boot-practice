package com.blog.application.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    @NotEmpty
    private String postTitle;
    @NotEmpty
    @Size(min = 20, message = "content must be min of 20 characters !!")
    private String postContent;
    private Boolean isLive;
    private String image;
    private Date createdDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comment = new HashSet<>();
}
