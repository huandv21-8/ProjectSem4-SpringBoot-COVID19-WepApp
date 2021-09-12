package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.dto.response.ICountPeopleByProvince;
import com.example.footballshopwebapp.entity.StatusByTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusByTimeRepository extends JpaRepository<StatusByTime, Long> {
    @Query(value = "select status_by_time.* from status_by_time " +
            "INNER JOIN (select status_by_time.people_id, max(status_by_time.updated_at) as updated_at from status_by_time " +
            "INNER JOIN people on people.people_id = status_by_time.people_id WHERE people.active = true group by people_id) " +
            "max_time ON status_by_time.updated_at=max_time.updated_at" +
            " where status_by_time.people_id=max_time.people_id AND status_by_time.status = :status",nativeQuery = true)
    List<StatusByTime> getAllPeopleByStatusWhereActiveTrue(@Param("status") String status);

    @Query(value = "SELECT peo.status_by_time_id,peo.id_source,peo.status,peo.travel_schedule,peo.type,peo.people_id," +
            " MAX(peo.updated_at) AS updated_at FROM status_by_time as peo " +
            "INNER JOIN people on people.people_id = peo.people_id WHERE people.active = true " +
            "AND peo.people_id = :peopleId",nativeQuery = true)
   Optional <StatusByTime> getPeopleByStatusAndIdPeopleAndMaxTime(@Param("peopleId") Long peopleId);


    Optional<StatusByTime> findByStatusByTimeId(Long statusByTimeId);


    @Query(value = "select status_by_time.* from status_by_time \n" +
            " INNER JOIN people on people.people_id = status_by_time.people_id\n" +
            " WHERE people.active = true\n" +
            "AND status_by_time.status = :status",nativeQuery = true)
    List<StatusByTime> findAllByStatusAndActiveTrue(@Param("status") String status);

    @Query(value = "SELECT province.province_id as provinceId, province.province_name as provinceName " +
            ", COUNT(b.people_id) as countPeople FROM province LEFT JOIN\n" +
            "(SELECT a.people_id as people_id, district.district_id ,district.province_id AS province_id from district\n" +
            "INNER JOIN\n" +
            "(SELECT people.people_id as people_id, people.name ,commune.commune_id, commune.district_id as district_id FROM people\n" +
            "INNER JOIN status_by_time ON status_by_time.people_id = people.people_id\n" +
            "INNER JOIN commune on commune.commune_id= people.commune_id\n" +
            "WHERE status_by_time.status = :status AND people.active= true ) a\n" +
            "ON district.district_id = a.district_id) b\n" +
            "ON province.province_id = b.province_id GROUP BY province.province_id",nativeQuery = true)
    List<ICountPeopleByProvince> listCountPeopleByProvince(@Param("status") String status);
}
