package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Cured;
import com.example.footballshopwebapp.entity.F1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CuredRepository extends JpaRepository<Cured, Long> {
//    List<Cured> findAllByActiveTrue();
    Cured findByCuredId(Long id);

    @Query(value = "Select * from cured inner join ( Select max(time) as LatestDate, people_id as id from cured Group by people_id ) SubMax on cured.time = SubMax.LatestDate and cured.people_id = SubMax.id WHERE active = true AND status = 'cured'",nativeQuery = true)
    List<Cured> listCuredByActiveTrue();

//    List<Cured> findAllByTimeEqualsAndStatus(Date time, String status);
//    List<Cured> findAllByTime(LocalDate time, String status);

    List<Cured> findAllByTimeEqualsAndStatus(Date time, String cured);
    List<Cured> findAllByStatus( String cured);
}
