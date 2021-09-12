package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.response.CountPeopleByProvince;
import com.example.footballshopwebapp.dto.response.CountPeopleByStatusAboutProvince;

import com.example.footballshopwebapp.dto.response.ICountPeopleByProvince;
import com.example.footballshopwebapp.entity.StatusByTime;
import com.example.footballshopwebapp.repository.StatusByTimeRepository;
import com.example.footballshopwebapp.service.StatisticalPeopleService;

import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticalPeopleServiceImp implements StatisticalPeopleService {

    private final DateHelper dateHelper;
    private final StatusByTimeRepository statusByTimeRepository;




    @Override
    public Map<String, TreeMap<Date, Integer>> dashboard(String timeForm) {
        Map<String, TreeMap<Date, Integer>> mapByStatus = null;
        if (timeForm != null) {
            mapByStatus = new HashMap<>();
            TreeMap<Date, Integer> mapCuredByTime = new TreeMap<>();
            TreeMap<Date, Integer> mapSickByTime = new TreeMap<>();
            TreeMap<Date, Integer> mapDiedByTime = new TreeMap<>();
            List<Date> listDate;

            List<StatusByTime> curedList = new ArrayList<>();
            List<StatusByTime> diedList = new ArrayList<>();
            List<StatusByTime> sickList = new ArrayList<>();

            String formTime;

            if (timeForm.equals(VariableCommon.DAY)) {
                listDate = dateHelper.getDatesBetweenDay(Calendar.DAY_OF_MONTH, 30);
                formTime = "dd/MM/yyyy";
            } else if (timeForm.equals(VariableCommon.MONTH)) {
                listDate = dateHelper.getDatesBetweenDay(Calendar.MONTH, 12);
                formTime = "MM/YYYY";
            } else {
                listDate = dateHelper.getDatesBetweenDay(Calendar.YEAR, 3);
                formTime = "YYYY";
            }

            listDate.forEach(time -> {

                String strTime = dateHelper.convertDateToString(time, formTime);

                for (StatusByTime sick : statusByTimeRepository.findAllByStatusAndActiveTrue(VariableCommon.SICK)) {
                    if (strTime.equals(dateHelper.convertDateToString(sick.getUpdatedAt(), formTime))) {
                        sickList.add(sick);
                    }
                }
                mapSickByTime.put(time, sickList.size());
                sickList.clear();

                for (StatusByTime died : statusByTimeRepository.findAllByStatusAndActiveTrue(VariableCommon.DIED)) {
                    if (strTime.equals(dateHelper.convertDateToString(died.getUpdatedAt(), formTime))) {
                        diedList.add(died);
                    }
                }
                mapDiedByTime.put(time, diedList.size());
                diedList.clear();

                for (StatusByTime cured : statusByTimeRepository.findAllByStatusAndActiveTrue(VariableCommon.CURED)) {
                    if (strTime.equals(dateHelper.convertDateToString(cured.getUpdatedAt(), formTime))) {
                        curedList.add(cured);
                    }
                }
                mapCuredByTime.put(time, curedList.size());
                curedList.clear();

            });

            mapByStatus.put(VariableCommon.CURED, mapCuredByTime);
            mapByStatus.put(VariableCommon.SICK, mapSickByTime);
            mapByStatus.put(VariableCommon.DIED, mapDiedByTime);
        }
        return mapByStatus;
    }

    @Override
    public List<CountPeopleByStatusAboutProvince> countPeopleByStatusAboutProvince() {
        List<ICountPeopleByProvince> iCountCuredByProvinces = statusByTimeRepository.listCountPeopleByProvince(VariableCommon.CURED);

        List<CountPeopleByProvince> countPeopleByProvinces = iCountCuredByProvinces.stream()
                .map(ICountPeopleByProvince::get).collect(Collectors.toList());


        return null;


    }
}
