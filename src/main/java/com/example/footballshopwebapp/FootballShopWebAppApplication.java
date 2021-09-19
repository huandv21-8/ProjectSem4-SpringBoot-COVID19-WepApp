package com.example.footballshopwebapp;

//import com.example.footballshopwebapp.dto.response.CountPeopleByProvince;
import com.example.footballshopwebapp.dto.response.CountPeopleByProvince;
import com.example.footballshopwebapp.dto.response.ICountPeopleByProvince;
import com.example.footballshopwebapp.repository.StatusByTimeRepository;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class FootballShopWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballShopWebAppApplication.class, args);
	}

//	@Autowired
//	private StatusByTimeRepository statusByTimeRepository;
//
//	@PostConstruct
//	public void test() {
//
//	}

}
