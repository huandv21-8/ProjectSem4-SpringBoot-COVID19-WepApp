package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.Province;
import com.example.footballshopwebapp.entity.StatusByTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    @Mapping(target = "idPeople", source = "statusByTime.people.peopleId")
    @Mapping(target = "name", source = "statusByTime.people.name")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "nameProvince", source = "province.provinceName")
    @Mapping(target = "status", source = "statusByTime.status")
    @Mapping(target = "type", source = "statusByTime.type")
    @Mapping(target = "idStatusByTime", source = "statusByTime.statusByTimeId")
    PeopleResponseAdmin peopleResponseAdminMap(StatusByTime statusByTime, Province province,String birthDay);


}
