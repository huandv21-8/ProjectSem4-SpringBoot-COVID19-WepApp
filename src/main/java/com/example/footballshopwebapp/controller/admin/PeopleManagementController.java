package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.service.PeopleManagementService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/managementPeople")
public class PeopleManagementController {

    private final PeopleManagementService peopleManagementService;


    @PostMapping("/createPeople")
    public Message createPeople(@RequestBody PeopleRequest peopleRequest) throws SpringException {
     return peopleManagementService.createPeople(peopleRequest);
//        return new Message("succ");
    }
}
