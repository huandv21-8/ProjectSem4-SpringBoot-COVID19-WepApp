package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.F1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface F1Repository extends JpaRepository<F1,Long> {

    List<F1> findAllByActiveTrue();
    List<F1> findAllBySickSource_SickId(Long idSickSource);
//    List<F1> findAllBySickSource_SickId
}
