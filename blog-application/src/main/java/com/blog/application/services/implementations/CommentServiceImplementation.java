package com.blog.application.services.implementations;

import com.blog.application.entities.Comment;
import com.blog.application.entities.Post;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.CommentDto;
import com.blog.application.repositories.CommentRepository;
import com.blog.application.repositories.PostRepository;
import com.blog.application.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setCreatedDate(new Date());
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deletedComment(Integer commentId) {
        Comment comment  = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "comment id", commentId));
        this.commentRepository.delete(comment);
    }
}
