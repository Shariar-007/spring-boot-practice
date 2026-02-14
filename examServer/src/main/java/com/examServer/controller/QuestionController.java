package com.examServer.controller;

import com.examServer.entity.exam.Category;
import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.entity.exam.Question;
import com.examServer.entity.exam.Quiz;
import com.examServer.helper.Views;
import com.examServer.services.OptionService;
import com.examServer.services.QuestionService;
import com.examServer.services.QuizService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
@Tag(name = "Question Details")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private OptionService optionService;

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        Question localQuestion = questionService.addQuestions(question);
        return ResponseEntity.ok(localQuestion);
    }

    @PutMapping
    public Question UpdateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @JsonView(Views.Public.class)
    @GetMapping
    public Set<Question> getQuestions() {
        return this.questionService.getQuestions();

    }

    @GetMapping("/{questionId}")
    public Question getQuestionById(@PathVariable("questionId") Long id) {
        return this.questionService.getQuestion(id);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") Long id) {
        Quiz quiz = quizService.getQuiz(id);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long id) {
        Quiz quiz = this.quizService.getQuiz(id);
        Set<Question> questions = quiz.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long id) {
        this.questionService.deleteQuestion(id);
    }

    // eval quiz
    @JsonView(Views.Admin.class)
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        Double marksGot = 0.0;
        Integer correctAnswers = 0;
        Integer attempted = 0;

        for (Question item : questions) {
            Set<Long> userAnswers = item.getGivenAnswer();
            Question question = this.questionService.getQuestion(item.getQuesId());
            Set<OptionOfQuestion> correctOptionsObjects = this.optionService.getOptionByValidityForQuestion(question.getQuesId(), true);
            Set<Long> correctOptionIds = correctOptionsObjects.stream().map(OptionOfQuestion::getOId).collect(Collectors.toSet());
            if (userAnswers != null && userAnswers.equals(correctOptionIds)) {
                correctAnswers++;

                // Calculate marks
                double maxMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks());
                double markSingle = maxMarks / questions.size();
                marksGot += markSingle;
            }

            if (item.getGivenAnswer() != null) {
                attempted++;
            }
        }
        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);
    }
}
