package com.grapeup.quizservice.service.label;

import com.grapeup.quizservice.domain.Label;
import com.grapeup.quizservice.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public Label getOrCreateLabel(String labelName) {
        Optional<Label> labelOpt = labelRepository.findByName(labelName);
        return labelOpt
                .orElseGet(() ->
                    labelRepository.save(Label.builder().name(labelName).build())
                );
    }
}
