package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
     Province findByProvinceId(Long id);

     @Query(value = "SELECT p.* FROM district as d\n" +
             "INNER JOIN province as p on d.province_id = p.province_id\n" +
             "INNER JOIN commune as c on c.district_id = d.district_id\n" +
             "WHERE c.commune_id = :communeId",nativeQuery = true)
     public Province getProvinceByCommune(@Param("communeId") Long communeId);
}
