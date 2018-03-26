package com.grapeup.quizservice.controller;

import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN')")
    public QuestionDto createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.create(questionDto);
    }

    @GetMapping
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    public Page<QuestionDto> getQuestions(Pageable pagable) {
        return questionService.qetQuestions(pagable);
    }

    @GetMapping("/labels")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    public List<QuestionDto> findQuestionsByLabels(@RequestParam("name") List<String> labels) {
        return questionService.findQuesionsByLabels(labels);
    }

    @PutMapping("/{questionId}")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    public QuestionDto updateQuestion(
            @PathVariable("questionId") String questionId,
            @RequestBody QuestionDto questionDto
    ) {
        return questionService.updateQuestion(questionId, questionDto);
    }
}
