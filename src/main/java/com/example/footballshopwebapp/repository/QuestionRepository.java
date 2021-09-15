package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
