package com.grapeup.quizservice.service.quiz;

import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.dto.QuizDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Page<QuizDto> getAllActiveQuizzes(Pageable pageable);

    QuizDto createQuiz(QuizDto quizDto);

    QuizDto updateQuiz(String quizId, QuizDto quizDto);

    void deleteQuiz(String quizId);

    QuizDto addQuestionToQuiz(String quizId, QuestionDto questionDto);
}
