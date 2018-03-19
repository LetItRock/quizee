package com.grapeup.quizservice.controller;

import com.grapeup.quizservice.dto.QuizDto;
import com.grapeup.quizservice.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    @Value("${some.value}")
    private String value;

    @Autowired
    private QuizService quizService;

    @GetMapping(path = "/active")
    @PreAuthorize("#oauth2.hasScope('ui')")
    public Page<QuizDto> getAllActiveQuizzes(Pageable pageable) {
        return quizService.getAllActiveQuizzes(pageable);
    }

    @PostMapping(path = "/create")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN')")
    public QuizDto createQuiz(QuizDto quizDto) {
        return quizService.createQuiz(quizDto);
    }
}
