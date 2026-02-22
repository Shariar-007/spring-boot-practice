package com.blog.application.services.implementations;

import com.blog.application.entities.Category;
import com.blog.application.entities.Post;
import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.PostDao;
import com.blog.application.payloads.PostResponse;
import com.blog.application.repositories.CategoryRepository;
import com.blog.application.repositories.PostRepository;
import com.blog.application.repositories.UserRepository;
import com.blog.application.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDao createPost(PostDao postDao, Integer userId, Integer categoryId) {
        Post post = this.modelMapper.map(postDao, Post.class);
        if(post.getImage() == null || post.getImage() != "") {
            post.setImage("default.png");
        }
        post.setCreatedDate(new Date());

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        post.setUser(user);
        Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        post.setCategory(category);

        Post savedPost = postRepository.save(post);
        return this.modelMapper.map(savedPost, PostDao.class);
    }

    @Override
    public PostDao updatePost(PostDao postDao, Integer postId) {
        Post foundedPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        foundedPost.setTitle(postDao.getPostTitle());
        foundedPost.setContent(postDao.getPostContent());
        foundedPost.setImage(postDao.getImage());
        Post updatedPost = postRepository.save(foundedPost);
        return this.modelMapper.map(updatedPost, PostDao.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        postRepository.delete(post);
    }

    @Override
    public PostDao getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        return modelMapper.map(post, PostDao.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        Sort sort = null;
        if(sortType.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagedPost = postRepository.findAll(pageable);

        List<Post> posts = pagedPost.getContent();
        List<PostDao> postDaos =  posts.stream().map((post) -> modelMapper.map(post, PostDao.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDaos);
        postResponse.setPageNumber(pagedPost.getNumber());
        postResponse.setPageSize(pagedPost.getSize());
        postResponse.setTotalElements(pagedPost.getTotalElements());
        postResponse.setTotalPages(pagedPost.getTotalPages());
        postResponse.setLastPage(pagedPost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostsByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        Sort sort = null;
        if(sortType.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else if (sortType.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        User foundedUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagedPost = postRepository.findAllByUser(foundedUser, pageable);
        List<Post> posts = pagedPost.getContent();
        List<PostDao> postDaos =  posts.stream().map((post) -> modelMapper.map(post, PostDao.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDaos);
        postResponse.setPageNumber(pagedPost.getNumber());
        postResponse.setPageSize(pagedPost.getSize());
        postResponse.setTotalElements(pagedPost.getTotalElements());
        postResponse.setTotalPages(pagedPost.getTotalPages());
        postResponse.setLastPage(pagedPost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostsByCategoryId(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        Sort sort = null;
        if(sortType.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else if (sortType.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagedPost = postRepository.findAllByCategory(foundedCategory, pageable);
        List<Post> posts = pagedPost.getContent();
        List<PostDao> postDaos =  posts.stream().map((post) -> modelMapper.map(post, PostDao.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDaos);
        postResponse.setPageNumber(pagedPost.getNumber());
        postResponse.setPageSize(pagedPost.getSize());
        postResponse.setTotalElements(pagedPost.getTotalElements());
        postResponse.setTotalPages(pagedPost.getTotalPages());
        postResponse.setLastPage(pagedPost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDao> searchPostByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);
        List<PostDao> postDaos =  posts.stream().map((post) -> modelMapper.map(post, PostDao.class)).collect(Collectors.toList());
        return postDaos;
    }
}
