package com.grapeup.quizservice.service.quiz;

import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Page<Quiz> getAllActiveQuizzes(Pageable pageable) {
        return  quizRepository.findByActive(true, pageable);
    }
}
