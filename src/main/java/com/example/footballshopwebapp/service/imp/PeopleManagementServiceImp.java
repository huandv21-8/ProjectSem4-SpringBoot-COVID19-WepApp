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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
//@Transactional
public class PeopleManagementServiceImp implements PeopleManagementService {

    private final PeopleRepository peopleRepository;
    private final SickRepository sickRepository;
    private final CommuneRepository communeRepository;
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final F1Repository f1Repository;
    private final CuredRepository curedRepository;
    private final DiedRepository diedRepository;
    private final PeopleMapper peopleMapper;
    private final PeopleDetailResponseMapper peopleDetailResponseMapper;
    private final DateHelper dateHelper;


    @Override
    @Transactional
    public Message createPeople(PeopleRequest peopleRequest) throws SpringException {
        try {

            if (peopleRequest.getPhone() == null && !peopleRequest.getPhone().matches("[0-9]+") && peopleRequest.getPhone().length() == 10) {
                throw new SpringException("SĐT phải là số và đủ 10 số.");
            }

            People people = new People();
            people.setName(peopleRequest.getName());
            people.setAge(peopleRequest.getAge());
            people.setGender(peopleRequest.isGender());
            people.setPhone(peopleRequest.getPhone());
            people.setSchedule(peopleRequest.getSchedule());
            people.setCommune(communeRepository.findByCommuneId(peopleRequest.getIdCommune()));
            people.setDistrict(districtRepository.findByDistrictId(peopleRequest.getIdDistrict()));
            people.setProvince(provinceRepository.findByProvinceId(peopleRequest.getIdProvince()));
            people.setTime(dateHelper.getDateNow());

            if (peopleRequest.getStatus().equals(VariableCommon.SICK)) {
                Sick sick = new Sick();
                sick.setPeople(peopleRepository.save(people));
                sick.setStatus(peopleRequest.getStatus());
                sick.setTime(dateHelper.getDateNow());
                sick.setType(peopleRequest.isType());
                sick.setActive(true);
                if (peopleRequest.isType()) {
                    sick.setIdSickSource(peopleRequest.getIdSourced());
                }
                sickRepository.save(sick);
            } else if (peopleRequest.getStatus().equals(VariableCommon.F1)) {
                F1 f1 = new F1();
                f1.setPeople(peopleRepository.save(people));
                f1.setSickSource(sickRepository.findBySickId(peopleRequest.getIdSourced()));
                f1.setStatus(peopleRequest.getStatus());
                f1.setType(true);
                f1.setActive(true);
                f1.setTime(dateHelper.getDateNow());
                f1Repository.save(f1);
            }
            return new Message("Thanh cong");
        } catch (Exception e) {
            throw new SpringException("sai roi");
        }

    }

    @Override
    public List<PeopleResponseAdmin> getAllPeopleByStatus(String status) {
        List<PeopleResponseAdmin> peopleResponseAdminList = null;
        if (status.equals(VariableCommon.SICK)) {
            List<Sick> list1 = sickRepository.listSickByActiveTrue();
            peopleResponseAdminList = list1.stream().map(sick -> peopleMapper.sickResponseAdminMap(sick)).collect(Collectors.toList());
        } else if (status.equals(VariableCommon.F1)) {
            peopleResponseAdminList = f1Repository.listF1ByActiveTrue().stream().map(peopleMapper::f1ResponseAdminMap).collect(Collectors.toList());
        } else if (status.equals(VariableCommon.CURED)) {
            peopleResponseAdminList = curedRepository.listCuredByActiveTrue().stream().map(peopleMapper::curedResponseAdminMap).collect(Collectors.toList());
        } else if (status.equals(VariableCommon.DIED)) {
            peopleResponseAdminList = diedRepository.findAllByActiveTrue().stream().map(peopleMapper::diedResponseAdminMap).collect(Collectors.toList());
        }

        return peopleResponseAdminList;
    }

    @Override
    public PeopleDetailResponseAdmin peopleDetailByStatus(String status, Long idPeople) {
        PeopleDetailResponseAdmin peopleDetailResponseAdmin = null;
        if (status.equals(VariableCommon.SICK)) {
            Sick sick = sickRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào có id là: " + idPeople));
            peopleDetailResponseAdmin = peopleDetailResponseMapper.sickDetailResponseAdminMap(sick);
        } else if (status.equals(VariableCommon.F1)) {
            F1 f1 = f1Repository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người f1 nào có id là: " + idPeople));
            peopleDetailResponseAdmin = peopleDetailResponseMapper.f1DetailResponseAdminMap(f1);
        } else if (status.equals(VariableCommon.CURED)) {
            Cured cured = curedRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào đã khỏi có id là: " + idPeople));
            peopleDetailResponseAdmin = peopleDetailResponseMapper.curedDetailResponseAdminMap(cured);
        } else if (status.equals(VariableCommon.DIED)) {
            Died died = diedRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào đã khỏi có id là: " + idPeople));
            peopleDetailResponseAdmin = peopleDetailResponseMapper.diedDetailResponseAdminMap(died);
        }
        if (peopleDetailResponseAdmin.getIdSource() != null) {
            Sick sick = sickRepository.findById(peopleDetailResponseAdmin.getIdSource()).orElseThrow(() -> new SpringException("Không có người bệnh nào có id là: " + idPeople));
            peopleDetailResponseAdmin.setNameSource(sick.getPeople().getName());
        }
        return peopleDetailResponseAdmin;
    }

    @Override
    @Transactional
    public Message deletePeopleById(String status, Long idPeople) throws SpringException {
        try {
            if (status.equals(VariableCommon.SICK)) {
                Sick sick = sickRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào có id là: " + idPeople));
                sick.setActive(false);

                List<F1> listF1 = f1Repository.findAllBySickSource_SickId(sick.getSickId());
                if (listF1 != null) {
                    for (F1 f1 : listF1) {
                        f1.setActive(false);
                        f1Repository.save(f1);
                    }
                }
                sickRepository.save(sick);
            } else if (status.equals(VariableCommon.F1)) {
                F1 f1 = f1Repository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người f1 nào có id là: " + idPeople));
                f1.setActive(false);
                f1Repository.save(f1);
            } else if (status.equals(VariableCommon.CURED)) {
                Cured cured = curedRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào đã khỏi có id là: " + idPeople));
                cured.setActive(false);
                curedRepository.save(cured);
            } else if (status.equals(VariableCommon.DIED)) {
                Died died = diedRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào đã khỏi có id là: " + idPeople));
                died.setActive(false);
                diedRepository.save(died);
            }
            return new Message("Xóa thành công.");
        } catch (Exception e) {
            throw new SpringException("Lỗi rồi.");
        }

    }

    @Override
    @Transactional
    public Message deleteAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        for (Long id : listIdPeopleCheckbox) {
            deletePeopleById(status, id);
        }
        return new Message("Xóa thành công.");
    }


}
