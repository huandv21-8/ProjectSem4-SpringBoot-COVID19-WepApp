package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestionId(Long questionId);


    @Query(value = "SELECT * FROM question WHERE account_id = :accountId ORDER BY created_at ASC", nativeQuery = true)
    List<Question> findAllByAccountIdAndOrderByCreatedAtAsc(@Param("accountId") Long accountId);

@Query(value = "SELECT * FROM question WHERE account_id = :accountId ORDER BY created_at DESC", nativeQuery = true)
    List<Question> findAllByAccountIdAndOrderByCreatedAtDesc(@Param("accountId") Long accountId);


    @Query(value = "Select question.* from question" +
            " inner join (SELECT MAX(created_at) as max_time FROM question" +
            "  inner join account on account.account_id = question.account_id where account.phone = :phone)a \n" +
            "            on a.max_time = question.created_at ", nativeQuery = true)
    Question detailDeclareRecent(@Param("phone") String phone);

    @Query(value = "Select question.* from question inner join (SELECT MAX(created_at) as max_time FROM question " +
            "inner join account on account.account_id = question.account_id GROUP BY account.account_id) " +
            "a on a.max_time = question.created_at", nativeQuery = true)
    List<Question> listQuestionRecent();

    @Query(value = "Select question.* from question inner join (SELECT MAX(created_at) as max_time FROM question \n" +
            "            inner join (SELECT account.account_id as account_id FROM account WHERE active= true AND name" +
            " LIKE %:name%  AND birth_day LIKE %:birthDay% AND phone LIKE %:phone%) b on b.account_id = question.account_id GROUP BY b.account_id) \n" +
            "            a on a.max_time = question.created_at;", nativeQuery = true)
    List<Question> findAllAccountSearch(@Param("name") String name,
                                        @Param("birthDay") String birthDay,
                                        @Param("phone") String phone);

}
