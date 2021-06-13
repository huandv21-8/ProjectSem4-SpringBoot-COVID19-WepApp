package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.F1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface F1Repository extends JpaRepository<F1,Long> {
}
