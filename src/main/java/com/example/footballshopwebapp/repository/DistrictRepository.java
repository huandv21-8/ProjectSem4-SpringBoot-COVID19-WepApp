package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
     List<District> findAllByProvince_ProvinceId(Long idProvince);
}
