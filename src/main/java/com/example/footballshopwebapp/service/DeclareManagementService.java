package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.dto.response.AccountResponseByAll;
import com.example.footballshopwebapp.dto.response.DeclareResponse;
import com.example.footballshopwebapp.dto.response.QuestionResponse;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.entity.Question;
import com.example.footballshopwebapp.share.Message;

import java.util.List;

public interface DeclareManagementService {
    QuestionResponse declare(DeclareRequest declareRequest);

    AccountResponse findAccountByPhone(String phone);

    Message createAccount(AccountRequest accountRequest);

     List<Question> listDeclare();

    QuestionResponse detailDeclare(Long questionId);

    Message updateAccount(AccountRequest accountRequest);

    List<AccountResponseByAll> listAccount();

    Message managementAccount(String optionChoose,Long accountId);

    Message managementAllAccountByCheckBox(String optionChoose,List<Long> listAccountIdCheckbox);

    AccountResponseByAll detailAccount(Long accountId);

    List<AccountResponseByAll> listAccountSearch(String phone, String name, String birthDay, Long provinceId);

    List<DeclareResponse> listDeclareByAccountId(Long accountId, String orderByDate);

    QuestionResponse detailDeclareRecent(String phone);
}
