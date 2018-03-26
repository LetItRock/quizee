package com.grapeup.quizservice.service.question;

import com.grapeup.quizservice.dto.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    QuestionDto create(QuestionDto questionDto);

    Page<QuestionDto> qetQuestions(Pageable pagable);

    List<QuestionDto> findQuesionsByLabels(List<String> labels);

    QuestionDto updateQuestion(String questionId, QuestionDto questionDto);

    void deleteQuestion(String questionId);
}
