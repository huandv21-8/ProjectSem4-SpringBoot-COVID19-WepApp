package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.dto.response.AccountResponseByAll;
import com.example.footballshopwebapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "name", source = "account.name")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "gender", source = "account.gender")
    @Mapping(target = "cmt", source = "account.cmt")
    @Mapping(target = "communeId", source = "account.commune.communeId")
    @Mapping(target = "address", source = "account.address")
    @Mapping(target = "phone", source = "account.phone")
    AccountResponse accountResponseMap(Account account,String birthDay);

    @Mapping(target = "accountId", source = "account.accountId")
    @Mapping(target = "name", source = "account.name")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "gender", source = "account.gender")
    @Mapping(target = "cmt", source = "account.cmt")
    @Mapping(target = "provinceName", source = "account.commune.district.province.provinceName")
    @Mapping(target = "communeName", source = "account.commune.communeName")
    @Mapping(target = "districtName", source = "account.commune.district.districtName")
    @Mapping(target = "address", source = "account.address")
    @Mapping(target = "phone", source = "account.phone")
    @Mapping(target = "dateDeclare", source = "dateDeclare")
    AccountResponseByAll accountResponseByAllMap(Account account, String birthDay,String dateDeclare);
}
