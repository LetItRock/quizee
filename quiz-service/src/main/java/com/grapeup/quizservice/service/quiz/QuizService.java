package com.grapeup.quizservice.service.quiz;

import com.grapeup.quizservice.domain.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Page<Quiz> getAllQuizzes(Pageable pageable);
}
