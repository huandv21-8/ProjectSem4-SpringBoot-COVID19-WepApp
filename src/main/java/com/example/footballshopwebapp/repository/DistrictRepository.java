package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
     District findByDistrictId(Long id);
}
