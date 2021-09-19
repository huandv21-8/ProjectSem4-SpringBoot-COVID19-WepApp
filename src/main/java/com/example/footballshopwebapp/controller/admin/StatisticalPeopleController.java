package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.response.CountPeopleByProvince;
import com.example.footballshopwebapp.service.PeopleManagementService;
import com.example.footballshopwebapp.service.StatisticalPeopleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/v1/statistical")
@AllArgsConstructor
public class StatisticalPeopleController {

    private final PeopleManagementService peopleManagementService;
    private final StatisticalPeopleService statisticalPeopleService;

    //error
    @GetMapping("/dashboard")
    public Map<String, TreeMap<Date, Integer>> dashboard(@RequestParam(required = false, name = "timeForm") String timeForm) {
        return statisticalPeopleService.dashboard(timeForm);
    }

    @GetMapping("/staticalTotalPeopleByStatus")
    public Map<String, Long> staticalTotalPeopleByStatus(){
        return peopleManagementService.staticalTotalPeopleByStatus();
    }


    @GetMapping("/countPeopleByStatusAboutProvince")
    public List<CountPeopleByProvince> countPeopleByStatusAboutProvince(){
        return statisticalPeopleService.countPeopleByStatusAboutProvince();
    }


}
