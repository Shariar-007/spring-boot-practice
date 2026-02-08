package com.examServer.services.implementation;

import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.entity.exam.Question;
import com.examServer.repository.OptionRepository;
import com.examServer.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class OptionServiceImplementation implements OptionService {
    @Autowired
    private OptionRepository optionRepository;
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
    public Set<OptionOfQuestion> getOptionOfQuestion(Question question) {
        return new LinkedHashSet<>(optionRepository.findByQuestion(question));
    }

    @Override
    public Set<OptionOfQuestion> getOptionByValidityForQuestion(Question question, Boolean b) {
        return new LinkedHashSet<>(optionRepository.findByIsCorrectAnswerAndQuestion(question,b));
    }

    @Override
    public void deleteOption(Long optionId) {

    }
}
