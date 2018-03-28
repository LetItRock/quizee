package com.grapeup.quizservice.controller;

import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.dto.QuizDto;
import com.grapeup.quizservice.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {
    @Value("${some.value}")
    private String value;

    @Autowired
    private QuizService quizService;

    @GetMapping("/active")
    @PreAuthorize("#oauth2.hasScope('ui')")
    public Page<QuizDto> getAllActiveQuizzes(Pageable pageable) {
        return quizService.getAllActiveQuizzes(pageable);
    }

    @PutMapping("/{quizId}")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    public QuizDto updateQuiz(@PathVariable("quizId") String quizId, @RequestBody QuizDto quizDto) {
        return quizService.updateQuiz(quizId, quizDto);
    }

    @PostMapping("/create")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    public QuizDto createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto);
    }

    @DeleteMapping("/{quizId}")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuiz(@PathVariable("quizId") String quizId) {
        quizService.deleteQuiz(quizId);
    }

    @PostMapping("/{quizId}/questions")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    public QuizDto addQuestionToQuiz(@PathVariable("quizId") String quizId, @RequestBody QuestionDto questionDto) {
        return quizService.addQuestionToQuiz(quizId, questionDto);
    }

    @DeleteMapping("/{quizId}/questions/{questionId}")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN') or #oauth2.hasScope('server')")
    @ResponseStatus(HttpStatus.OK)
    public void removeQuestionFromQuiz(
            @PathVariable("quizId") String quizId,
            @PathVariable("questionId") String questionId
    ) {
        quizService.removeQuestionFromQuiz(quizId, questionId);
    }
}
