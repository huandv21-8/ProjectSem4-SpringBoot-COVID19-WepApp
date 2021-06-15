package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.Sick;

import java.util.List;

public interface SickService {
    List<PeopleResponseAdmin> getAllSick();
}
