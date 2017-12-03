package com.grapeup.quizservice.controller;

import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.dto.QuizDto;
import com.grapeup.quizservice.service.quiz.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuizController {
    @Value("${some.value}")
    private String value;

    @Autowired
    private QuizService quizService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/active")
    @PreAuthorize("#oauth2.hasScope('ui')")
    public Page<QuizDto> getAllActiveQuizzes(Pageable pageable) {
        return new PageImpl<>(convertToDtos(quizService.getAllActiveQuizzes(pageable)));
    }

    private List<QuizDto> convertToDtos(Page<Quiz> quizPage) {
        return quizPage
                .getContent()
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class))
                .collect(Collectors.toList());
    }
}
