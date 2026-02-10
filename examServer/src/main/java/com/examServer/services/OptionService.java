package com.examServer.services;

import com.examServer.entity.exam.OptionOfQuestion;
import com.examServer.entity.exam.Question;

import java.util.Set;

public interface OptionService {
    public OptionOfQuestion addOption(OptionOfQuestion option);
    public OptionOfQuestion updateOption(OptionOfQuestion option);
    public Set<OptionOfQuestion> getOptions();
    public OptionOfQuestion getOption(Long optionId);
    public Set<OptionOfQuestion> getOptionOfQuestion(Long questionId);
    public Set<OptionOfQuestion> getOptionByValidityForQuestion(Long questionId, Boolean b);
    public void deleteOption(Long optionId);
}
