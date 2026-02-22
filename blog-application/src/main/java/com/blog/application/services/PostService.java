package com.blog.application.services;

import com.blog.application.payloads.PostDao;
import com.blog.application.payloads.PostResponse;

import java.util.List;

public interface PostService {
    public PostDao createPost(PostDao postDao, Integer userId, Integer categoryId);

    public PostDao updatePost(PostDao postDao, Integer postId);

    public void deletePost(Integer postId);

    public PostDao getPostById(Integer postId);

    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    public PostResponse getPostsByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    public PostResponse getPostsByCategoryId(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    public List<PostDao> searchPostByTitle(String title);
}
