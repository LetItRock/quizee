package com.grapeup.quizservice.repository;

import com.grapeup.quizservice.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String>, QueryDslPredicateExecutor<Question> {
    List<Question> findByLabelsIn(List<String> labels);
}
