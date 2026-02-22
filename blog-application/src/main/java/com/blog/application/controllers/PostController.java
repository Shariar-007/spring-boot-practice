package com.blog.application.controllers;

import com.blog.application.configurations.AppConstants;
import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.PostDao;
import com.blog.application.payloads.PostResponse;
import com.blog.application.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Api/Post")
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDao> createPost(@Valid @RequestBody PostDao postDao,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDao createdPost = postService.createPost(postDao, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDao> updatePost(@Valid @RequestBody PostDao postDao, @PathVariable Integer postId) {
        PostDao updatedPost = postService.updatePost(postDao, postId);
        return new ResponseEntity<PostDao>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                       @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                       @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                       @RequestParam(value = "sortType", defaultValue = AppConstants.SORT_DIR, required = false) String sortType) {
        PostResponse postResponse = postService.getPostsByUserId(userId, pageNumber, pageSize, sortBy, sortType);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                           @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                           @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                           @RequestParam(value = "sortType", defaultValue = AppConstants.SORT_DIR, required = false) String sortType) {
        PostResponse postResponse = postService.getPostsByCategoryId(categoryId, pageNumber, pageSize, sortBy, sortType);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                 @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                 @RequestParam(value = "sortType", defaultValue = AppConstants.SORT_DIR, required = false) String sortType) {
        PostResponse pagepost = postService.getAllPosts(pageNumber, pageSize, sortBy, sortType);
        return new ResponseEntity<PostResponse>(pagepost, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDao> getPostById(@PathVariable Integer postId) {
        PostDao post = postService.getPostById(postId);
        return new ResponseEntity<PostDao>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDao>> searchPostByTitle(@RequestParam(name = "title", required = true) String keyWords) {
        List<PostDao> posts = postService.searchPostByTitle(keyWords);
        return new ResponseEntity<List<PostDao>>(posts, HttpStatus.OK);
    }

    // post api
//    @PostMapping("/post/image/upload/{postId}")
//    public ResponseEntity<PostDao> uploadPostImage(@RequestParam("image") MultipartFile image,
//                                                   @PathVariable Integer postId) throws IOException {
//        PostDao postDao = postService.getPostById(postId);
//        String fileName = fileService.uploadImage(path, image);
//        postDao.setImage(fileName);
//        PostDao updatedPost = this.postService.updatePost(postDao, postId);
//        return new ResponseEntity<PostDao>(updatedPost, HttpStatus.OK);
//    }

    // method to serve files

//    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException{
//        InputStream resource = fileService.getResource(path, imageName);
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(resource, response.getOutputStream());
//    }
}
