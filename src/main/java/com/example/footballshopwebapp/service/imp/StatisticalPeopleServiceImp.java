package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.entity.Cured;
import com.example.footballshopwebapp.entity.Died;
import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.repository.CuredRepository;
import com.example.footballshopwebapp.repository.DiedRepository;
import com.example.footballshopwebapp.repository.SickRepository;
import com.example.footballshopwebapp.service.StatisticalPeopleService;
import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class StatisticalPeopleServiceImp implements StatisticalPeopleService {
    private final DateHelper dateHelper;
    private final CuredRepository curedRepository;
    private final SickRepository sickRepository;
    private final DiedRepository diedRepository;


    @Override
    public Map<String, TreeMap<Date, Integer>> dashboard(String timeForm) {
        Map<String, TreeMap<Date, Integer>> mapByStatus = null;
        if (timeForm != null) {
            mapByStatus = new HashMap<>();
            TreeMap<Date, Integer> mapCuredByTime = new TreeMap<>();
            TreeMap<Date, Integer> mapSickByTime = new TreeMap<>();
            TreeMap<Date, Integer> mapDiedByTime = new TreeMap<>();
            List<Date> listDate;
            if (timeForm.equals(VariableCommon.DAY)) {
                listDate = dateHelper.getDatesBetweenDay(Calendar.DAY_OF_MONTH, 30);
                System.out.println(listDate.size());
                listDate.forEach(time -> {
                    System.out.println(dateHelper.convertDateToString(time,"dd/MM/YYYY"));
                    List<Cured> curedList = curedRepository.findAllByTimeEqualsAndStatus(time, VariableCommon.CURED);
                    mapCuredByTime.put(time, curedList.size());
                    System.out.println(mapCuredByTime);
                    curedList.clear();

                    List<Sick> sickList = sickRepository.findAllByTimeEqualsAndStatus(time, VariableCommon.SICK);
                    mapSickByTime.put(time, sickList.size());
                    sickList.clear();

                    List<Died> diedList = diedRepository.findAllByTimeEquals(time);
                    mapDiedByTime.put(time, diedList.size());
                    diedList.clear();
                });
            }
            if (timeForm.equals(VariableCommon.MONTH) || timeForm.equals(VariableCommon.YEAR)) {
                List<Cured> curedList = new ArrayList<>();
                List<Sick> sickList = new ArrayList<>();
                List<Died> diedList = new ArrayList<>();
                String formTime;

                if (timeForm.equals(VariableCommon.MONTH)) {
                    listDate = dateHelper.getDatesBetweenDay(Calendar.MONTH, 12);
                    formTime = "MM/YYYY";
                } else {
                    listDate = dateHelper.getDatesBetweenDay(Calendar.YEAR, 3);
                    formTime = "YYYY";
                }
                listDate.forEach(time -> {

                    String strTime = dateHelper.convertDateToString(time, formTime);

                    for (Cured cured : curedRepository.findAllByStatus(VariableCommon.CURED)) {
                        if (strTime.equals(dateHelper.convertDateToString(cured.getTime(), formTime))) {
                            curedList.add(cured);
                        }
                    }
                    mapCuredByTime.put(time, curedList.size());
                    curedList.clear();

                    for (Sick sick : sickRepository.findAllByStatus(VariableCommon.SICK)) {
                        if (strTime.equals(dateHelper.convertDateToString(sick.getTime(), formTime))) {
                            sickList.add(sick);
                        }
                    }
                    mapSickByTime.put(time, sickList.size());
                    sickList.clear();

                    for (Died died : diedRepository.findAllByActiveTrue()) {
                        if (strTime.equals(dateHelper.convertDateToString(died.getTime(), formTime))) {
                            diedList.add(died);
                        }
                    }
                    mapDiedByTime.put(time, diedList.size());
                    diedList.clear();
                });
            }

            mapByStatus.put(VariableCommon.CURED, mapCuredByTime);
            mapByStatus.put(VariableCommon.SICK, mapSickByTime);
            mapByStatus.put(VariableCommon.DIED, mapDiedByTime);
        }
        return mapByStatus;
    }
}
