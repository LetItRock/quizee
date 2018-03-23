package com.grapeup.quizservice.controller;

import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN')")
    public QuestionDto createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.create(questionDto);
    }
}
