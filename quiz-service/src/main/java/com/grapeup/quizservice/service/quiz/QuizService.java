package com.grapeup.quizservice.service.quiz;

import com.grapeup.quizservice.dto.QuizDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {
    Page<QuizDto> getAllActiveQuizzes(Pageable pageable);

    QuizDto createQuiz(QuizDto quizDto);
}
