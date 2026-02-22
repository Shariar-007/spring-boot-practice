package com.blog.application.repositories;

import com.blog.application.entities.Category;
import com.blog.application.entities.Post;
import com.blog.application.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public Page<Post> findAllByCategory(Category category, Pageable pageable);

    public Page<Post> findAllByUser(User user, Pageable pageable);

    public List<Post> findByTitleContainingIgnoreCase(String title);
}
