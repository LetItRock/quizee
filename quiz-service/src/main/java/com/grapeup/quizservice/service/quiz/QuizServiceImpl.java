package com.grapeup.quizservice.service.quiz;

import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.dto.QuizDto;
import com.grapeup.quizservice.repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Page<QuizDto> getAllActiveQuizzes(Pageable pageable) {
        return new PageImpl<>(convertToDtos(quizRepository.findByActive(true, pageable)));
    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        quizDto.setId(null);
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    private List<QuizDto> convertToDtos(Page<Quiz> quizPage) {
        return quizPage
                .getContent()
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class))
                .collect(Collectors.toList());
    }
}
