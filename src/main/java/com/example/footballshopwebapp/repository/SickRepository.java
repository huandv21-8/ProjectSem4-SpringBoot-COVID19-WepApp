package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Sick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SickRepository extends JpaRepository<Sick,Long> {
   Sick findBySickId(Long id);
}
