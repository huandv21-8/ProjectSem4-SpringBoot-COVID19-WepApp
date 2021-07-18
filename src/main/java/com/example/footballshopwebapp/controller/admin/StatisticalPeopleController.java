package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.service.StatisticalPeopleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/v1/statistical")
@AllArgsConstructor
public class StatisticalPeopleController {

    private final StatisticalPeopleService statisticalPeopleService;

    @GetMapping("/dashboard")
    public Map<String, TreeMap<Date, Integer>> dashboard(@RequestParam(required = false, name = "timeForm") String timeForm) {
        return statisticalPeopleService.dashboard(timeForm);
    }
}
