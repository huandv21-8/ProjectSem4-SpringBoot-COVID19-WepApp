package com.example.footballshopwebapp.controller;

import com.example.footballshopwebapp.entity.Commune;
import com.example.footballshopwebapp.entity.District;
import com.example.footballshopwebapp.entity.Province;
import com.example.footballshopwebapp.repository.CommuneRepository;
import com.example.footballshopwebapp.repository.DistrictRepository;
import com.example.footballshopwebapp.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/address")
@AllArgsConstructor
public class AddressController {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final CommuneRepository communeRepository;

    @GetMapping("/allProvince")
    public List<Province> allProvince(){
        return provinceRepository.findAll();
    }
    @GetMapping("/allDistrict")
    public List<District> allDistrict(){
        return districtRepository.findAll();
    }
    @GetMapping("/allCommune")
    public List<Commune> allCommune(){
        return communeRepository.findAll();
    }

    @GetMapping("/getAllDistrictByProvinceId/{idProvince}")
    public List<District> getAllDistrictByProvinceId(@PathVariable(value = "idProvince") Long idProvince){
        return districtRepository.findAllByProvince_ProvinceId(idProvince);
    }
    @GetMapping("/getAllCommuneByDistrictId/{idDistrict}")
    public List<Commune> getAllCommuneByDistrictId(@PathVariable(value = "idDistrict") Long idDistrict){
        return communeRepository.findAllByDistrict_DistrictId(idDistrict);
    }




}
