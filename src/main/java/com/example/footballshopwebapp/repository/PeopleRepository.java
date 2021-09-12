package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
   Optional<People> findByPeopleId(Long peopleId);
}
