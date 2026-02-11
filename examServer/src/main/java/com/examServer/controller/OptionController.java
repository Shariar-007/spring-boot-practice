package com.examServer.controller;

import com.examServer.entity.exam.Category;
import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.services.OptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option")
@CrossOrigin("*")
@Tag(name = "Options of Question")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping
    public ResponseEntity<?> addOption(@RequestBody OptionOfQuestion option) {
        OptionOfQuestion localOption = optionService.addOption(option);
        return ResponseEntity.ok(localOption);
    }

    @PutMapping
    public OptionOfQuestion updateOption(@RequestBody OptionOfQuestion option) {
        return optionService.updateOption(option);
    }

    @GetMapping
    public ResponseEntity<?> getOptions() {
        return ResponseEntity.ok(this.optionService.getOptions());
    }

    @GetMapping("/{optionId}")
    public OptionOfQuestion getOptionById(@PathVariable("optionId") Long id) {
        return this.optionService.getOption(id);
    }

    @GetMapping("question/{questionId}/options")
    public ResponseEntity<?> getOptionsOfQuestion(@PathVariable("questionId") Long questionId){
        return ResponseEntity.ok(this.optionService.getOptionOfQuestion(questionId));
    }

    @GetMapping("/question/{questionId}/optionsByStatus")
    public ResponseEntity<?> getOptionsByQuestionAndCorrectness(@PathVariable("questionId") Long questionId, @RequestParam(value = "status", defaultValue = "true") Boolean b){
        return ResponseEntity.ok(this.optionService.getOptionByValidityForQuestion(questionId, b));
    }
    @DeleteMapping("/{optionId}")
    public void deleteOption(@PathVariable("optionId") Long id) {
        this.optionService.deleteOption(id);
    }
}
