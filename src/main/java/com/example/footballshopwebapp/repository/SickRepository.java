package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Sick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface SickRepository extends JpaRepository<Sick,Long> {
   Sick findBySickId(Long id);

   @Query(value = "Select * from sick inner join ( Select max(time) as LatestDate, people_id as id from sick Group by people_id ) SubMax on sick.time = SubMax.LatestDate and sick.people_id = SubMax.id WHERE active = true AND status = 'sick'",nativeQuery = true)
   List<Sick> listSickByActiveTrue();

    List<Sick> findAllByTimeEqualsAndStatus(Date time, String sick);

    List<Sick> findAllByStatus(String sick);
}
