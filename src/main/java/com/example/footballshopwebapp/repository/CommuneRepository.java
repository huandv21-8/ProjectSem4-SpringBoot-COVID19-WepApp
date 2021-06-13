package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {
    Commune findByCommuneId(Long id);
}
