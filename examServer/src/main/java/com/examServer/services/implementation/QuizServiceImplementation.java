package com.examServer.services.implementation;

import com.examServer.entity.exam.Category;
import com.examServer.entity.exam.Quiz;
import com.examServer.repository.QuizRepository;
import com.examServer.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImplementation implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return quizRepository.findById(quizId).get();
    }

    @Override
    public List<Quiz> getQuizByCategory(Category category) {
        return quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes(Boolean b) {
        return quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category category, Boolean b) {
        return quizRepository.findByCategoryAndActive(category,true);
    }

    @Override
    public void deleteQuiz(Quiz quiz) {
        quizRepository.delete(quiz);
    }
}
