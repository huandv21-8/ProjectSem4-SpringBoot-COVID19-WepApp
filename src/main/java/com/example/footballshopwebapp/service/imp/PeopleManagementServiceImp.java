package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.Cured;
import com.example.footballshopwebapp.entity.F1;
import com.example.footballshopwebapp.entity.People;
import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.*;
import com.example.footballshopwebapp.service.PeopleManagementService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import com.example.footballshopwebapp.share.mapper.PeopleDetailResponseMapper;
import com.example.footballshopwebapp.share.mapper.PeopleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
    private final PeopleMapper peopleMapper;
    private final PeopleDetailResponseMapper peopleDetailResponseMapper;


    @Override
    @Transactional
    public Message createPeople(PeopleRequest peopleRequest) throws SpringException {
        try {

            if (!peopleRequest.getPhone().matches("[0-9]+") && peopleRequest.getPhone().length() == 10) {
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
            people.setTime(Instant.now());

            if (peopleRequest.getStatus().equals(VariableCommon.SICK)) {
                Sick sick = new Sick();
                sick.setPeople(peopleRepository.save(people));
                sick.setStatus(peopleRequest.getStatus());
                sick.setTime(Instant.now());
                sick.setType(peopleRequest.isType());
                if (peopleRequest.isType()) {
                    sick.setIdSickSource(peopleRequest.getIdSourced());
                }
                sickRepository.save(sick);
            } else if (peopleRequest.getStatus().equals(VariableCommon.F1)) {
                F1 f1 = new F1();
                f1.setPeople(peopleRepository.save(people));
                f1.setSickSource(sickRepository.findBySickId(peopleRequest.getIdSourced()));
                f1.setStatus(peopleRequest.getStatus());
                f1.setTime(Instant.now());
                f1Repository.save(f1);
            }
            return new Message("Thanh cong");
        } catch (Exception e) {
            throw new SpringException("sai roi");
        }

    }

    @Override
    public List<PeopleResponseAdmin> getAllPeopleByStatus(String status) {
        if (status.equals(VariableCommon.SICK)) {
            List<Sick> list1 = sickRepository.findAll();
            return list1.stream().map(sick -> peopleMapper.sickResponseAdminMap(sick)).collect(Collectors.toList());
        } else if (status.equals(VariableCommon.F1)) {
            return f1Repository.findAll().stream().map(peopleMapper::f1ResponseAdminMap).collect(Collectors.toList());
        } else if (status.equals(VariableCommon.CURED)) {
            return curedRepository.findAll().stream().map(peopleMapper::curedResponseAdminMap).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public PeopleDetailResponseAdmin peopleDetailByStatus(String status, Long idPeople) {
        if (status.equals(VariableCommon.SICK)) {
            Sick sick = sickRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào có id là: " + idPeople));

//            Date myDate = Date.from(sick.getTime());
            PeopleDetailResponseAdmin a= peopleDetailResponseMapper.sickDetailResponseAdminMap(sick);
            return a;
        } else if (status.equals(VariableCommon.F1)) {
            F1 f1 = f1Repository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người f1 nào có id là: " + idPeople));
            return peopleDetailResponseMapper.f1DetailResponseAdminMap(f1);
        } else if (status.equals(VariableCommon.CURED)) {
            Cured cured = curedRepository.findById(idPeople).orElseThrow(() -> new SpringException("Không có người bệnh nào đã khỏi có id là: " + idPeople));
            return peopleDetailResponseMapper.curedDetailResponseAdminMap(cured);
        }
        return null;
    }


}
