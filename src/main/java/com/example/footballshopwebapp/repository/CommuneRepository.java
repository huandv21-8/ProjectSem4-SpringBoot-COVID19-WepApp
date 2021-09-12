package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {
    Commune findByCommuneId(Long id);
    List<Commune>  findAllByDistrict_DistrictId(Long idDistrict);

}
