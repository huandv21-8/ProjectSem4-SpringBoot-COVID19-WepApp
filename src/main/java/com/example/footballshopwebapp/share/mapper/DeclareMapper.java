package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.dto.response.DeclareResponse;
import com.example.footballshopwebapp.dto.response.QuestionResponse;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeclareMapper {

    @Mapping(target = "questionId", source = "question.questionId")
    @Mapping(target = "name", source = "question.account.name")
    @Mapping(target = "updateAt", source = "updateAt")
    @Mapping(target = "provinceName", source = "question.account.commune.district.province.provinceName")
    @Mapping(target = "communeName", source = "question.account.commune.communeName")
    @Mapping(target = "districtName", source = "question.account.commune.district.districtName")
    @Mapping(target = "phone", source = "question.account.phone")
    DeclareResponse accountResponseMap(Question question, String updateAt);


    @Mapping(target = "name", source = "question.account.name")
    @Mapping(target = "cmt", source = "question.account.cmt")
    @Mapping(target = "gender", source = "question.account.gender")
    @Mapping(target = "phone", source = "question.account.phone")
    @Mapping(target = "updateAt", source = "updateAt")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "communeName", source = "question.account.commune.communeName")
    @Mapping(target = "districtName", source = "question.account.commune.district.districtName")
    @Mapping(target = "provinceName", source = "question.account.commune.district.province.provinceName")
    @Mapping(target = "address", source = "question.account.address")
    @Mapping(target = "exposureToF0", source = "question.exposureToF0")
    @Mapping(target = "comeBackFromEpidemicArea", source = "question.comeBackFromEpidemicArea")
    @Mapping(target = "contactWithPeopleReturningFromEpidemicAreas", source = "question.contactWithPeopleReturningFromEpidemicAreas")
    @Mapping(target = "fever", source = "question.fever")
    @Mapping(target = "cough", source = "question.cough")
    @Mapping(target = "shortnessOfBreath", source = "question.shortnessOfBreath")
    @Mapping(target = "pneumonia", source = "question.pneumonia")
    @Mapping(target = "soreThroat", source = "question.soreThroat")
    @Mapping(target = "tired", source = "question.tired")
    @Mapping(target = "chronicLiverDisease", source = "question.chronicLiverDisease")
    @Mapping(target = "chronicBloodDisease", source = "question.chronicBloodDisease")
    @Mapping(target = "chronicLungDisease", source = "question.chronicLungDisease")
    @Mapping(target = "chronicKideyDisease", source = "question.chronicKideyDisease")
    @Mapping(target = "heartRelatedDiseaes", source = "question.heartRelatedDiseaes")
    @Mapping(target = "highBloodPressure", source = "question.highBloodPressure")
    @Mapping(target = "hivOrImmunocompromised", source = "question.hivOrImmunocompromised")
    @Mapping(target = "organTransplantRecipient", source = "question.organTransplantRecipient")
    @Mapping(target = "diabetes", source = "question.diabetes")
    @Mapping(target = "cancer", source = "question.cancer")
    @Mapping(target = "pregnant", source = "question.pregnant")
    @Mapping(target = "travelSchedule", source = "question.travelSchedule")
    QuestionResponse questionResponseMap(Question question, String birthDay, String updateAt);

}
