package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.entity.Commune;
import com.example.footballshopwebapp.entity.District;
import com.example.footballshopwebapp.entity.Province;
import com.example.footballshopwebapp.entity.StatusByTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeopleDetailResponseMapper {

    @Mapping(target = "idPeople", source = "statusByTime.people.peopleId")
    @Mapping(target = "name", source = "statusByTime.people.name")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "phone", source = "statusByTime.people.phone")
    @Mapping(target = "gender", source = "statusByTime.people.gender")
    @Mapping(target = "travelSchedule", source = "statusByTime.travelSchedule")
    @Mapping(target = "province", source = "province.provinceName")
    @Mapping(target = "district", source = "district.districtName")
    @Mapping(target = "commune", source = "commune.communeName")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "status", source = "statusByTime.status")
    @Mapping(target = "type", source = "statusByTime.type")
    @Mapping(target = "idSource", source = "statusByTime.id_source")
    @Mapping(target = "nameSource", source = "nameSource")
    PeopleDetailResponseAdmin peopleDetailResponseAdminMap(
            StatusByTime statusByTime,
            Province province,
            District district,
            Commune commune,
            String birthDay,
            String updatedAt,
            String nameSource);

}
