package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    Optional<People> findByPeopleId(Long peopleId);

    @Query(value = "SELECT * FROM people WHERE people.birth_day = :birthDay AND people.cmt = :cmt AND people.gender = :gender " +
            "AND people.name= :name AND people.phone= :phone AND people.commune_id = :communeId", nativeQuery = true)
    People findPeople(@Param("birthDay") Date birthDay,
                      @Param("cmt") String cmt,
                      @Param("gender") boolean gender,
                      @Param("name") String name,
                      @Param("phone") String phone,
                      @Param("communeId") Long communeId);


}
