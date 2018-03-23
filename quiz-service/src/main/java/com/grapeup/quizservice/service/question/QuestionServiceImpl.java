package com.grapeup.quizservice.service.question;

import com.grapeup.quizservice.domain.Question;
import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.repository.QuestionRepository;
import com.grapeup.quizservice.service.label.LabelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LabelService labelService;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionDto create(QuestionDto questionDto) {
        Question question = getQuestionFromDto(questionDto);
        question.setCreated(LocalDateTime.now());
        question.setUpdated(LocalDateTime.now());
        return modelMapper.map(questionRepository.save(question), QuestionDto.class);
    }

    @Override
    public Page<QuestionDto> qetQuestions(Pageable pagable) {
        return new PageImpl<>(
                convertToDtos(questionRepository.findAll(pagable))
        );
    }

    private List<QuestionDto> convertToDtos(Page<Question> quizPage) {
        return quizPage
                .getContent()
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuestionDto.class))
                .collect(Collectors.toList());
    }

    private Question getQuestionFromDto(QuestionDto questionDto) {
        questionDto.setId(null);
        Question question = modelMapper.map(questionDto, Question.class);
        // because of wrong mapping between dto and entity
        // those labels from dto don't have id
        question.getLabels().clear();
        fillQuestionEntityWithLabels(questionDto, question);
        return question;
    }

    private void fillQuestionEntityWithLabels(QuestionDto questionDto, Question question) {
        questionDto.getLabels().forEach(labelDto -> {
            String labelName = labelDto.getName();
            question.addLabel(labelService.getOrCreateLabel(labelName));
        });
    }
}
