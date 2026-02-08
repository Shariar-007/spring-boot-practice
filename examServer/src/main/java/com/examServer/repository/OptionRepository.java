package com.examServer.repository;

import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.entity.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OptionRepository extends JpaRepository<OptionOfQuestion, Long> {
    Set<OptionOfQuestion> findByQuestion(Question question);
    Set<OptionOfQuestion> findByIsCorrectAnswerAndQuestion(Question question, Boolean b);
}
