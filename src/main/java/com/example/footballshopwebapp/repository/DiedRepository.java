package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Died;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiedRepository extends JpaRepository<Died,Long> {
    List<Died> findAllByActiveTrue();


    List<Died> findAllByTimeEquals(Date time);

}
