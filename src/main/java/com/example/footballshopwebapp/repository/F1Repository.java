package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.F1;
import com.example.footballshopwebapp.entity.Sick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface F1Repository extends JpaRepository<F1,Long> {

//    List<F1> findAllByActiveTrue();
    List<F1> findAllBySickSource_SickId(Long idSickSource);

    F1 findByF1Id(Long id);

    @Query(value = "Select * from f1 inner join ( Select max(time) as LatestDate, people_id as id from f1 Group by people_id ) SubMax on f1.time = SubMax.LatestDate and f1.people_id = SubMax.id WHERE active = true AND status = 'f1'",nativeQuery = true)
    List<F1> listF1ByActiveTrue();
}
