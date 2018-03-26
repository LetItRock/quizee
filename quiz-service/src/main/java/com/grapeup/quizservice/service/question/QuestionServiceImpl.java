package com.grapeup.quizservice.service.question;

import com.grapeup.quizservice.domain.Question;
import com.grapeup.quizservice.dto.QuestionDto;
import com.grapeup.quizservice.repository.QuestionRepository;
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
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public List<QuestionDto> findQuesionsByLabels(List<String> labels) {
        List<Question> questions = questionRepository.findByLabelsIn(labels);
        return convertToDtos(questions);
    }

    @Override
    public QuestionDto updateQuestion(String questionId, QuestionDto questionDto) {
        Question questionToUpdate = questionRepository.findOne(questionId);
        Assert.notNull(questionToUpdate, "Cannot find question with id: " + questionId);

        modelMapper.map(questionDto, questionToUpdate);

        return  modelMapper.map(questionRepository.save(questionToUpdate), QuestionDto.class);
    }

    @Override
    public void deleteQuestion(String questionId) {
        Question questionToDelete = questionRepository.findOne(questionId);
        Assert.notNull(questionToDelete, "Cannot find question with id: " + questionId);
        questionRepository.delete(questionToDelete);
    }

    private List<QuestionDto> convertToDtos(Page<Question> quizPage) {
        return convertToDtos(quizPage.getContent());
    }

    private List<QuestionDto> convertToDtos(List<Question> quizPage) {
        return quizPage
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuestionDto.class))
                .collect(Collectors.toList());
    }

    private Question getQuestionFromDto(QuestionDto questionDto) {
        questionDto.setId(null);
        return modelMapper.map(questionDto, Question.class);
    }
}
