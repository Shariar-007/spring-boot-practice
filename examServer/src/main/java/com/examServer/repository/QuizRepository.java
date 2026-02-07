package com.examServer.repository;

import com.examServer.entity.exam.Category;
import com.examServer.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(Category category);

    List<Quiz> findByActive(Boolean t);

    List<Quiz> findByCategoryAndActive(Category category, Boolean t);
}
