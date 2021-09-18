package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.share.Message;

public interface DeclareManagementService {
    Message declare(DeclareRequest declareRequest);

    AccountResponse findAccountByPhone(String phone);
}
