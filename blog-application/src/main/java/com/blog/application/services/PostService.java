package com.blog.application.services;

import com.blog.application.payloads.PostDto;
import com.blog.application.payloads.PostResponse;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    public PostDto updatePost(PostDto postDto, Integer postId);

    public void deletePost(Integer postId);

    public PostDto getPostById(Integer postId);

    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    public PostResponse getPostsByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    public PostResponse getPostsByCategoryId(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    public List<PostDto> searchPostByTitle(String title);
}
