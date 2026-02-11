package com.examServer.controller;

import com.examServer.entity.exam.Question;
import com.examServer.entity.exam.Quiz;
import com.examServer.services.QuestionService;
import com.examServer.services.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
@Tag(name = "Question Details")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        Question localQuestion = questionService.addQuestions(question);
        return ResponseEntity.ok(localQuestion);
    }

    @PutMapping
    public Question UpdateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @GetMapping
    public Set<Question> getQuestions() {
        return this.questionService.getQuestions();
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
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        Double marksGot = 0.0;
        Integer correctAnswers = 0;
        Integer attempted = 0;

//        for (Question item : questions) {
//            Question question = this.questionService.get(item.getQuesId());
//            if (question.getAnswer().equals(item.getGivenAnswer())) {
//                correctAnswers++;
//                double markSingle =  Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
//                marksGot += markSingle;
//            }
//
//            if (item.getGivenAnswer() != null) {
//                attempted++;
//            }
//        }
        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);
    }
}
