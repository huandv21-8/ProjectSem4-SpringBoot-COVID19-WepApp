package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.entity.Cured;
import com.example.footballshopwebapp.entity.Died;
import com.example.footballshopwebapp.entity.F1;
import com.example.footballshopwebapp.entity.Sick;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeopleDetailResponseMapper {

    @Mapping(target = "idPeople", source = "sickId")
    @Mapping(target = "name", source = "people.name")
    @Mapping(target = "age", source = "people.age")
    @Mapping(target = "phone", source = "people.phone")
    @Mapping(target = "gender", source = "people.gender")
    @Mapping(target = "schedule", source = "people.schedule")
    @Mapping(target = "province", source = "people.province.provinceName")
    @Mapping(target = "district", source = "people.district.districtName")
    @Mapping(target = "commune", source = "people.commune.communeName")
    @Mapping(target = "time", source = "time")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "idSource", source = "idSickSource")
    PeopleDetailResponseAdmin sickDetailResponseAdminMap(Sick sick);

    @Mapping(target = "idPeople", source = "f1Id")
    @Mapping(target = "name", source = "people.name")
    @Mapping(target = "age", source = "people.age")
    @Mapping(target = "phone", source = "people.phone")
    @Mapping(target = "gender", source = "people.gender")
    @Mapping(target = "schedule", source = "people.schedule")
    @Mapping(target = "province", source = "people.province.provinceName")
    @Mapping(target = "district", source = "people.district.districtName")
    @Mapping(target = "commune", source = "people.commune.communeName")
    @Mapping(target = "time", source = "time")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "idSource", source = "sickSource.sickId")
    PeopleDetailResponseAdmin f1DetailResponseAdminMap(F1 f1);

    @Mapping(target = "idPeople", source = "curedId")
    @Mapping(target = "name", source = "people.name")
    @Mapping(target = "age", source = "people.age")
    @Mapping(target = "phone", source = "people.phone")
    @Mapping(target = "gender", source = "people.gender")
    @Mapping(target = "schedule", source = "people.schedule")
    @Mapping(target = "province", source = "people.province.provinceName")
    @Mapping(target = "district", source = "people.district.districtName")
    @Mapping(target = "commune", source = "people.commune.communeName")
    @Mapping(target = "time", source = "time")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "idSource", source = "idSickSource")
    PeopleDetailResponseAdmin curedDetailResponseAdminMap(Cured cured);

    @Mapping(target = "idPeople", source = "diedId")
    @Mapping(target = "name", source = "sick.people.name")
    @Mapping(target = "age", source = "sick.people.age")
    @Mapping(target = "phone", source = "sick.people.phone")
    @Mapping(target = "gender", source = "sick.people.gender")
    @Mapping(target = "schedule", source = "sick.people.schedule")
    @Mapping(target = "province", source = "sick.people.province.provinceName")
    @Mapping(target = "district", source = "sick.people.district.districtName")
    @Mapping(target = "commune", source = "sick.people.commune.communeName")
    @Mapping(target = "time", source = "time")
//    @Mapping(target = "status",source = "status",defaultValue = "died")
   // @Mapping(target = "type", source = "type")
   // @Mapping(target = "idSource", source = "idSickSource")
    PeopleDetailResponseAdmin diedDetailResponseAdminMap(Died died);
}
