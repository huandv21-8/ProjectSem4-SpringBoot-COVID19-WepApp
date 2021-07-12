package com.example.footballshopwebapp.service;


import java.util.List;

public interface OptionPeopleManagementService {
    void moveCuredPeopleById(String status, Long idChoicePeople);

    void moveCuredAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox);

    void moveF1PeopleById(String status, Long idChoicePeople);

    void moveF1AllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox);

    void moveDiedPeopleById(String status, Long idChoicePeople);

    void moveDiedAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox);

    void moveSickPeopleById(String status, Long idChoicePeople);

    void moveSickAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox);
}
