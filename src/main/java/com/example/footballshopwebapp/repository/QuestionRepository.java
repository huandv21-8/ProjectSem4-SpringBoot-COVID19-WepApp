package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Optional<Question> findByQuestionId(Long questionId);
}
