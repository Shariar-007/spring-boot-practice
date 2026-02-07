package com.examServer.services;

import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.entity.exam.Question;

import java.util.Set;

public interface OptionService {
    public OptionOfQuestion addOption(OptionOfQuestion option);
    public OptionOfQuestion updateOption(OptionOfQuestion option);
    public Set<OptionOfQuestion> getOptions();
    public OptionOfQuestion getOption(Long optionId);
    public OptionOfQuestion getOptionOfQuestion(Question question);
    public OptionOfQuestion getOptionByValidityForQuestion(Question question, Boolean b);
    public void deleteOption(Long optionId);
}
