package com.example.footballshopwebapp.service;


import java.util.List;

public interface OptionPeopleManagementService {
    void movePeopleByStatusAndPeopleId(String status, Long idStatusByTime);

    void moveAllPeopleByStatusAndPeopleIdAndCheckbox(String status, List<Long> listIdPeopleCheckbox);
}
