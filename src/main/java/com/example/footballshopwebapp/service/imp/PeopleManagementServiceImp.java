package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.*;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.*;
import com.example.footballshopwebapp.service.PeopleManagementService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import com.example.footballshopwebapp.share.mapper.PeopleDetailResponseMapper;
import com.example.footballshopwebapp.share.mapper.PeopleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
//@Transactional
public class PeopleManagementServiceImp implements PeopleManagementService {

    private final PeopleRepository peopleRepository;
    private final CommuneRepository communeRepository;
    private final ProvinceRepository provinceRepository;
    private final StatusByTimeRepository statusByTimeRepository;
    private final PeopleMapper peopleMapper;
    private final PeopleDetailResponseMapper peopleDetailResponseMapper;
    private final DateHelper dateHelper;


    @Override
    @Transactional
    public Message createPeople(PeopleRequest peopleRequest) throws SpringException {
        try {
            People people = new People();
            people.setName(peopleRequest.getName());
            people.setBirthDay(peopleRequest.getBirthDay());
            people.setGender(peopleRequest.isGender());
            people.setPhone(peopleRequest.getPhone());
            people.setCommune(communeRepository.findByCommuneId(peopleRequest.getIdCommune()));
            people.setTime(dateHelper.getDateNow());
            people.setCmt(peopleRequest.getCmt());
            people.setActive(true);

            StatusByTime statusByTime = new StatusByTime();
            statusByTime.setPeople(peopleRepository.save(people));
            statusByTime.setStatus(peopleRequest.getStatus());
            statusByTime.setType(peopleRequest.isType());
            statusByTime.setUpdatedAt(dateHelper.getDateNow());
            statusByTime.setTravelSchedule(peopleRequest.getSchedule());
            if (peopleRequest.getIdSourced() != null && (peopleRequest.isType() || peopleRequest.getStatus().equals(VariableCommon.F1))) {
                statusByTime.setId_source(peopleRequest.getIdSourced());
            }
            statusByTimeRepository.save(statusByTime);

            return new Message("Thanh cong");
        } catch (Exception e) {
            throw new SpringException("sai roi " + e.getMessage());
        }

    }


    @Override
    public List<PeopleResponseAdmin> getAllPeopleByStatus(String status) {
        List<PeopleResponseAdmin> peopleResponseAdminList = null;
        if (status != null) {

            List<StatusByTime> statusByTimeList = statusByTimeRepository.getAllPeopleByStatusWhereActiveTrue(status);
            System.out.println(statusByTimeList);
            peopleResponseAdminList = statusByTimeList.stream().map((statusByTime) -> {
                String birthDay = dateHelper.convertDateToString(statusByTime.getPeople().getBirthDay(), "dd/MM/yyyy");
                return peopleMapper.peopleResponseAdminMap(statusByTime, provinceRepository.getProvinceByCommune(statusByTime.getPeople().getCommune().getCommuneId()), birthDay);
            }).collect(Collectors.toList());
        }
        return peopleResponseAdminList;
    }

    @Override
    public List<PeopleResponseAdmin> getAllPeopleByStatusAndSearch(String status, String name, String birthDay, Long provinceId) {
        List<PeopleResponseAdmin> peopleResponseAdminList = null;
        if (status != null) {
            List<StatusByTime> statusByTimeList = statusByTimeRepository.getAllPeopleByStatusWhereActiveTrueAndSearch(status, name, birthDay);
            System.out.println(statusByTimeList);
            peopleResponseAdminList = statusByTimeList.stream()
                    .filter((statusByTime -> {
                        if (provinceId == null || provinceId == 0) {
                            return true;
                        }
                        return (statusByTime.getPeople().getCommune().getDistrict().getProvince().getProvinceId() == provinceId);
                    }))
                    .map((statusByTime) -> {
                        String birthDay1 = dateHelper.convertDateToString(statusByTime.getPeople().getBirthDay(), "dd/MM/yyyy");
                        return peopleMapper.peopleResponseAdminMap(statusByTime, provinceRepository.getProvinceByCommune(statusByTime.getPeople().getCommune().getCommuneId()), birthDay1);
                    }).collect(Collectors.toList());
        }
        return peopleResponseAdminList;
    }


    @Override
    @Transactional
    public Message deletePeopleById(String status, Long idPeople) {
        try {
            Optional<People> people = Optional.ofNullable(peopleRepository.findByPeopleId(idPeople).
                    orElseThrow(() -> new SpringException("Không có người nào có id là: " + idPeople)));
            people.get().setActive(false);
            peopleRepository.save(people.get());

            return new Message("Xóa thành công.");
        } catch (Exception e) {
            throw new SpringException("Lỗi rồi.");
        }

    }

    @Override
    public Message deleteAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        for (Long id : listIdPeopleCheckbox) {
            deletePeopleById(status, id);
        }
        return new Message("Xóa thành công.");
    }


    @Override
    public PeopleDetailResponseAdmin peopleDetailByStatus(String status, Long idStatusByTime) {
        PeopleDetailResponseAdmin peopleDetailResponseAdmin = null;
        if (status != null && idStatusByTime != null) {
            StatusByTime statusByTime = statusByTimeRepository.findByStatusByTimeId(idStatusByTime).get();
            Province province = provinceRepository.getProvinceByCommune(statusByTime.getPeople().getCommune().getCommuneId());
            District district = statusByTime.getPeople().getCommune().getDistrict();
            Commune commune = statusByTime.getPeople().getCommune();
            String birthDay = dateHelper.convertDateToString(statusByTime.getPeople().getBirthDay(), "dd/MM/yyyy");
            String updatedAt = dateHelper.convertDateToString(statusByTime.getUpdatedAt(), "dd/MM/yyyy");
            String namePeopleSource = null;
            if (statusByTime.getId_source() != null && statusByTime.getId_source() != 0) {
                Optional<People> people = Optional.ofNullable(peopleRepository.findByPeopleId(statusByTime.getId_source()).
                        orElseThrow(() -> new SpringException("Không có người nào có id là: " + statusByTime.getId_source())));
                if (people != null) {
                    namePeopleSource = people.get().getName();
                }
            }
            peopleDetailResponseAdmin = peopleDetailResponseMapper.peopleDetailResponseAdminMap(
                    statusByTime,
                    province,
                    district,
                    commune,
                    birthDay,
                    updatedAt,
                    namePeopleSource);
        }

        return peopleDetailResponseAdmin;
    }


    @Override
    public Map<String, Long> staticalTotalPeopleByStatus() {
        Map<String, Long> totalPeople = new HashMap<>();
        totalPeople.put(VariableCommon.CURED, (long) statusByTimeRepository.getAllPeopleByStatusWhereActiveTrue(VariableCommon.CURED).size());
        totalPeople.put(VariableCommon.SICK, (long) statusByTimeRepository.getAllPeopleByStatusWhereActiveTrue(VariableCommon.SICK).size());
        totalPeople.put(VariableCommon.DIED, (long) statusByTimeRepository.getAllPeopleByStatusWhereActiveTrue(VariableCommon.DIED).size());
        totalPeople.put(VariableCommon.F1, (long) statusByTimeRepository.getAllPeopleByStatusWhereActiveTrue(VariableCommon.F1).size());
        return totalPeople;
    }


}
