package com.grapeup.quizservice.service.quiz;

import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.dto.QuizDto;
import com.grapeup.quizservice.repository.QuizRepository;
import com.grapeup.quizservice.service.question.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionService questionService;

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
        quiz.setCreated(LocalDateTime.now());
        quiz.setUpdated(LocalDateTime.now());
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    @Override
    public QuizDto updateQuiz(String quizId, QuizDto quizDto) {
        Quiz quiz = getOrThrowException(quizId);
        quiz.setUpdated(LocalDateTime.now());

        quiz.setName(quizDto.getName());
        quiz.setShortDescription(quizDto.getShortDescription());
        quiz.setDescription(quizDto.getDescription());
        quiz.setDuration(quizDto.getDuration());
        quiz.setPoints(quizDto.getPoints());
        quiz.setIcon(quizDto.getIcon());
        quiz.setShowResult(quizDto.isShowResult());
        quiz.setActive(quizDto.isActive());

        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    @Override
    public void deleteQuiz(String quizId) {
        Quiz quiz = getOrThrowException(quizId);
        quizRepository.delete(quiz);
    }

    @Override
    public QuizDto addQuestionToQuiz(String quizId, QuestionDto questionDto) {
        Quiz quiz = getOrThrowException(quizId);
        quiz.addQuestion(questionService.getOrCreateQuestion(questionDto));

        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    private Quiz getOrThrowException(String quizId) {
        Quiz quiz = quizRepository.findOne(quizId);
        Assert.notNull(quiz, "Cannot find quiz with id: " + quizId);
        return quiz;
    }

    private List<QuizDto> convertToDtos(Page<Quiz> quizPage) {
        return quizPage
                .getContent()
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class))
                .collect(Collectors.toList());
    }
}
