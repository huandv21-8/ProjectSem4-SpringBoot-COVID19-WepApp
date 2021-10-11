package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestionId(Long questionId);


    @Query(value = "SELECT * FROM question WHERE account_id = :accountId ORDER BY created_at ASC", nativeQuery = true)
    List<Question> findAllByAccountIdAndOrderByCreatedAtAsc(@Param("accountId") Long accountId);

@Query(value = "SELECT * FROM question WHERE account_id = :accountId ORDER BY created_at DESC", nativeQuery = true)
    List<Question> findAllByAccountIdAndOrderByCreatedAtDesc(@Param("accountId") Long accountId);


}
