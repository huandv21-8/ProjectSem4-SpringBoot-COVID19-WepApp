package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.Cured;
import com.example.footballshopwebapp.entity.Died;
import com.example.footballshopwebapp.entity.F1;
import com.example.footballshopwebapp.entity.Sick;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    @Mapping(target = "idPeople", source = "sickId")
    @Mapping(target = "name", source = "people.name")
    @Mapping(target = "age", source = "people.age")
    @Mapping(target = "nameProvince", source = "people.province.provinceName")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    PeopleResponseAdmin sickResponseAdminMap(Sick sick);

    @Mapping(target = "idPeople", source = "f1Id")
    @Mapping(target = "name", source = "people.name")
    @Mapping(target = "age", source = "people.age")
    @Mapping(target = "nameProvince", source = "people.province.provinceName")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", defaultValue = "false")
    PeopleResponseAdmin f1ResponseAdminMap(F1 f1);

    @Mapping(target = "idPeople", source = "curedId")
    @Mapping(target = "name", source = "people.name")
    @Mapping(target = "age", source = "people.age")
    @Mapping(target = "nameProvince", source = "people.province.provinceName")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    PeopleResponseAdmin curedResponseAdminMap(Cured cured);

    @Mapping(target = "idPeople", source = "diedId")
    @Mapping(target = "name", source = "sick.people.name")
    @Mapping(target = "age", source = "sick.people.age")
    @Mapping(target = "nameProvince", source = "sick.people.province.provinceName")
//    @Mapping(target = "status", source = "status")
//    @Mapping(target = "type", source = "type")
    PeopleResponseAdmin diedResponseAdminMap(Died died);

}
