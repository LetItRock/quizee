package com.grapeup.quizservice.repository;

import com.grapeup.quizservice.domain.Label;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface LabelRepository extends MongoRepository<Label, String>, QueryDslPredicateExecutor<Label> {
}
