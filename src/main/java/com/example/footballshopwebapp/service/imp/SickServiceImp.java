package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.repository.SickRepository;
import com.example.footballshopwebapp.service.SickService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SickServiceImp implements SickService {
    private final SickRepository sickRepository;
    @Override
    public List<Sick> getAllSick() {
        return sickRepository.findAll();
    }
}
