package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.entity.F1;
import com.example.footballshopwebapp.entity.People;
import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.*;
import com.example.footballshopwebapp.service.PeopleManagementService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
@Transactional
public class PeopleManagementServiceImp implements PeopleManagementService {

    private final PeopleRepository peopleRepository;
    private final SickRepository sickRepository;
    private final CommuneRepository communeRepository;
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final F1Repository f1Repository;

    @Override
    public Message createPeople(PeopleRequest peopleRequest) throws SpringException {
        try {

            if(!peopleRequest.getPhone().matches("[0-9]+") && peopleRequest.getPhone().length() == 10){
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
}
