package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {
}
