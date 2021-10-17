package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.dataCovidStatisticalNcov.DataCallApiJsonNcov;
import com.example.footballshopwebapp.dto.response.CountPeopleByProvince;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface StatisticalPeopleService {
    Map<String, TreeMap<Date,Integer>> dashboard(String timeForm);

    List<CountPeopleByProvince> countPeopleByStatusAboutProvince();

    DataCallApiJsonNcov testDataCovid() throws Exception;
}
