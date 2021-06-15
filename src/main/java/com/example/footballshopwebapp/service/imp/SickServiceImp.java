package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.repository.SickRepository;
import com.example.footballshopwebapp.service.SickService;
import com.example.footballshopwebapp.share.mapper.PeopleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SickServiceImp implements SickService {
    private final SickRepository sickRepository;
    private final PeopleMapper peopleMapper;

    @Override
    public List<PeopleResponseAdmin> getAllSick() {
        List<Sick> list1 = sickRepository.findAll();
        return list1.stream().map(sick -> peopleMapper.sickResponseAdminMap(sick)).collect(Collectors.toList());
    }
}
