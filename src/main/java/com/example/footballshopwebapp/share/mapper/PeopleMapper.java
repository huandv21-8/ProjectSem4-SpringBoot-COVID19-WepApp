package com.example.footballshopwebapp.share.mapper;

import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.Sick;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    @Mapping(target = "idPeople" ,source = "sickId")
    @Mapping(target = "name" ,source = "people.name")
    @Mapping(target = "age" ,source = "people.age")
    @Mapping(target = "nameProvince" ,source = "people.province.provinceName")
    @Mapping(target = "status" ,source = "status")
    @Mapping(target = "type" ,source = "type")
    PeopleResponseAdmin sickResponseAdminMap(Sick sick);

}
