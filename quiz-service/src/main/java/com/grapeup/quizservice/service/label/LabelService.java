package com.grapeup.quizservice.service.label;

import com.grapeup.quizservice.domain.Label;

public interface LabelService {
    Label getOrCreateLabel(String labelName);
}
