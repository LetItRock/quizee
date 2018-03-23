package com.grapeup.quizservice.service.question;

import com.grapeup.quizservice.dto.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    QuestionDto create(QuestionDto questionDto);

    Page<QuestionDto> qetQuestions(Pageable pagable);
}
