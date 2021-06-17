package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Cured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuredRepository extends JpaRepository<Cured, Long> {
}
