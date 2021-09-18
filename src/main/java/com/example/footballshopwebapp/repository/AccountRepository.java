package com.example.footballshopwebapp.repository;

import com.example.footballshopwebapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountId(Long accountId);
    Account findByPhone(String phone);
}
