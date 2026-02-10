package com.examServer.services.implementation;

import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.entity.exam.Question;
import com.examServer.repository.OptionRepository;
import com.examServer.repository.QuestionRepository;
import com.examServer.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class OptionServiceImplementation implements OptionService {
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public OptionOfQuestion addOption(OptionOfQuestion option) {
        return optionRepository.save(option);
    }

    @Override
    public OptionOfQuestion updateOption(OptionOfQuestion option) {
        return optionRepository.save(option);
    }

    @Override
    public Set<OptionOfQuestion> getOptions() {
        return new LinkedHashSet<>(optionRepository.findAll());
    }

    @Override
    public OptionOfQuestion getOption(Long optionId) {
        return optionRepository.findById(optionId).get();
    }

    @Override
    public Set<OptionOfQuestion> getOptionOfQuestion(Long questionId) {
        Question local = questionRepository.findById(questionId).get();
        return new LinkedHashSet<>(optionRepository.findByQuestion(local));
    }

    @Override
    public Set<OptionOfQuestion> getOptionByValidityForQuestion(Long questionId, Boolean b) {
        Question local = questionRepository.findById(questionId).get();
        return new LinkedHashSet<>(optionRepository.findByIsCorrectAnswerAndQuestion(local,b));
    }

    @Override
    public void deleteOption(Long optionId) {

    }
}
