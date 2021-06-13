package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.share.Message;

public interface PeopleManagementService {

    Message createPeople(PeopleRequest peopleRequest) throws SpringException;

}
