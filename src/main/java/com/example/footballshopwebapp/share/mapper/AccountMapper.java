package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "name", source = "account.name")
    @Mapping(target = "birthDay", source = "account.birthDay")
    @Mapping(target = "gender", source = "account.gender")
    @Mapping(target = "cmt", source = "account.cmt")
    @Mapping(target = "communeId", source = "account.commune.communeId")
    @Mapping(target = "address", source = "account.address")
    AccountResponse accountResponseMap(Account account);
}
