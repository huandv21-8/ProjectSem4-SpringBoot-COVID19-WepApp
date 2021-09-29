package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.share.Message;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PeopleManagementService {

    Message createPeople(PeopleRequest peopleRequest) throws SpringException;

    List<PeopleResponseAdmin> getAllPeopleByStatus(String status) ;

    PeopleDetailResponseAdmin peopleDetailByStatus(String status,Long idStatusByTime);

    Message deletePeopleById(String status, Long idPeople);

    Message deleteAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox);

    Map<String, Long> staticalTotalPeopleByStatus();

    List<PeopleResponseAdmin> getAllPeopleByStatusAndSearch(String status, String name, String birthDay, Long provinceId);
}
