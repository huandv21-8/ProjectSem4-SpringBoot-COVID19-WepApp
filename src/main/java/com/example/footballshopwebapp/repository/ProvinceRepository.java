package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
     Province findByProvinceId(Long id);

}
