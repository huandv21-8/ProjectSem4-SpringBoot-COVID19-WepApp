package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.share.Message;

import java.util.List;

public interface PeopleManagementService {

    Message createPeople(PeopleRequest peopleRequest) throws SpringException;

    List<PeopleResponseAdmin> getAllPeopleByStatus(String status);

    PeopleDetailResponseAdmin peopleDetailByStatus(String status,Long idPeople);

    Message deletePeopleById(String status, Long idPeople);

    Message deleteAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox);
}
