package com.examServer.services;

import com.examServer.entity.exam.Category;
import com.examServer.entity.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public List<Quiz> getQuizByCategory(Category category);

    public List<Quiz> getActiveQuizzes(Boolean b);
    public List<Quiz> getActiveQuizzesOfCategory(Category category, Boolean b);
    public void deleteQuiz(Quiz quiz);
}
