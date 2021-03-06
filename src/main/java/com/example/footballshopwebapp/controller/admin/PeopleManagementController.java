package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.PeopleRequest;
import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.dto.response.PeopleResponseAdmin;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.service.PeopleManagementService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/managementPeople")
public class PeopleManagementController {

    private final PeopleManagementService peopleManagementService;


    @PostMapping("/createPeople")
    public Message createPeople(@RequestBody @Validated PeopleRequest peopleRequest) throws SpringException {
        return peopleManagementService.createPeople(peopleRequest);
    }

    @GetMapping("/listPeople")
    public ResponseEntity<List<PeopleResponseAdmin>> listPeopleByStatus(
            @RequestParam(required = false, name="status") String status) throws ParseException {
        return status(HttpStatus.OK).body(peopleManagementService.getAllPeopleByStatus(status));
    }
    @GetMapping("/listPeopleSearch")
    public ResponseEntity<List<PeopleResponseAdmin>> listPeopleByStatusAndSearch(
            @RequestParam(required = false, name="status") String status,
            @RequestParam(required = false, name="name") String name,
            @RequestParam( name="birthDay") String birthDay,
            @RequestParam( required = false, name="provinceId") Long provinceId
            ) throws ParseException {

        return status(HttpStatus.OK).body(peopleManagementService.getAllPeopleByStatusAndSearch(status,name,birthDay,provinceId));

    }

    @GetMapping("/peopleDetailByStatus")
    public ResponseEntity<PeopleDetailResponseAdmin> peopleDetailByStatus(
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(required = false, name = "idStatusByTime") Long idStatusByTime) {
        return status(HttpStatus.OK).body(peopleManagementService.peopleDetailByStatus(status, idStatusByTime));
    }

    @DeleteMapping("/deletePeopleById/{idPeople}")
    public ResponseEntity<Void> deletePeopleById(
            @RequestParam(required = false, name = "status") String status,
            @PathVariable Long idPeople) {
        peopleManagementService.deletePeopleById(status, idPeople);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteAllPeopleByCheckbox")
    public ResponseEntity<Void> deleteAllPeopleByCheckbox(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody List<Long> listIdPeopleCheckbox) {
        peopleManagementService.deleteAllPeopleByCheckbox(status, listIdPeopleCheckbox);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
