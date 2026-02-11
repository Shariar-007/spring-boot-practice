package com.examServer.services.implementation;

import com.examServer.entity.exam.Question;
import com.examServer.entity.exam.Quiz;
import com.examServer.repository.QuestionRepository;
import com.examServer.repository.QuizRepository;
import com.examServer.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImplementation implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Question addQuestions(Question question) {
        Long qid = question.getQuiz().getQId();
        Quiz quiz = quizRepository.findById(qid).orElseThrow(() -> new RuntimeException("Quiz not found"));
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question = this.questionRepository.findById(questionId).get();
        this.questionRepository.delete(question);
    }
}
