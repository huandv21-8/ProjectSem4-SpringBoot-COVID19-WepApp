package com.example.footballshopwebapp.service.imp;


import com.example.footballshopwebapp.entity.StatusByTime;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.StatusByTimeRepository;
import com.example.footballshopwebapp.service.OptionPeopleManagementService;

import com.example.footballshopwebapp.share.helper.DateHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OptionPeopleManagementServiceImp implements OptionPeopleManagementService {
    private final DateHelper dateHelper;
    private final StatusByTimeRepository statusByTimeRepository;


    @Override
    public void movePeopleByStatusAndPeopleId(String status, Long idStatusByTime) {
        Optional<StatusByTime> statusByTime = Optional.ofNullable(statusByTimeRepository.
                getPeopleByStatusAndIdPeopleAndMaxTime(idStatusByTime).orElseThrow(() -> new SpringException("Ko ton tai")));

        StatusByTime newStatusByTime = new StatusByTime();
        newStatusByTime.setPeople(statusByTime.get().getPeople());
        newStatusByTime.setStatus(status);
        newStatusByTime.setType(statusByTime.get().isType());
        newStatusByTime.setUpdatedAt(dateHelper.getDateNow());
        newStatusByTime.setTravelSchedule(statusByTime.get().getTravelSchedule());
        newStatusByTime.setId_source(statusByTime.get().getId_source());
        statusByTimeRepository.save(newStatusByTime);
    }

    @Override
    public void moveAllPeopleByStatusAndPeopleIdAndCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        listIdPeopleCheckbox.stream().forEach(id -> {
            movePeopleByStatusAndPeopleId(status, id);
        });
    }

}
