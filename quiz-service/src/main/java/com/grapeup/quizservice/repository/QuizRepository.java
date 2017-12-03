package com.grapeup.quizservice.repository;

import com.grapeup.quizservice.domain.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String>, QueryDslPredicateExecutor<Quiz> {
    Page<Quiz> findByActive(boolean active, Pageable pageable);
}
