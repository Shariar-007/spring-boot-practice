package com.examServer.services;

import com.examServer.entity.exam.Question;
import com.examServer.entity.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    public Question addQuestions(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionsOfQuiz(Quiz quiz);
    public void deleteQuestion(Long questionId);
//    public Question get(Long questionId);
}
