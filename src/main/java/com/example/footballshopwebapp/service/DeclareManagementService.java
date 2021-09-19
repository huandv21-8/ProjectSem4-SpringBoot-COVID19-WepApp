package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.entity.Question;
import com.example.footballshopwebapp.share.Message;

import java.util.List;

public interface DeclareManagementService {
    Message declare(DeclareRequest declareRequest);

    AccountResponse findAccountByPhone(String phone);

    Message createAccount(AccountRequest accountRequest);

     List<Question> listDeclare();

    Question detailDeclare(Long questionId);
}
