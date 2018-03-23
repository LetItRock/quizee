package com.grapeup.quizservice.repository;

import com.grapeup.quizservice.domain.Label;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.Optional;

public interface LabelRepository extends MongoRepository<Label, String>, QueryDslPredicateExecutor<Label> {
    Optional<Label> findByName(String name);
}
