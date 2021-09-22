package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {
    @Query(value = "SELECT * FROM situation ORDER BY updated_at DESC",nativeQuery = true)
    List<Situation> findAllOrderByUpdatedAtDesc();

}
