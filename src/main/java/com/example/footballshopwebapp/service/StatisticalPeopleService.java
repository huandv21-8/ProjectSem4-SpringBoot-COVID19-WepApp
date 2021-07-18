package com.example.footballshopwebapp.service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public interface StatisticalPeopleService {
    Map<String, TreeMap<Date,Integer>> dashboard(String timeForm);
}
