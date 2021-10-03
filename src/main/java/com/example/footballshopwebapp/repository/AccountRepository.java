package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountId(Long accountId);

    @Query(value = "Select account.* from account inner join (SELECT MAX(time) as max_time FROM account WHERE phone =:phone )a on a.max_time = account.time",nativeQuery = true)
    Account findByAccPhone(@Param("phone") String phone);

    List<Account> findAllByActiveTrue();
    Account findAccountByAccountId(Long accountId);
}
