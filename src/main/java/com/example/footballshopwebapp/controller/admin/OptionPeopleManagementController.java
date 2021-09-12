package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.service.OptionPeopleManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/optionPeople")
@AllArgsConstructor
public class OptionPeopleManagementController {
    private final OptionPeopleManagementService optionPeopleManagementService;

    @PostMapping("movePeopleByStatusAndPeopleId")
    public ResponseEntity<Void> movePeopleByStatusAndPeopleId(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody Long idChoicePeople) {
        optionPeopleManagementService.movePeopleByStatusAndPeopleId(status, idChoicePeople);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveAllPeopleByStatusAndPeopleIdAndCheckbox")
    public ResponseEntity<Void> moveAllPeopleByStatusAndPeopleIdAndCheckbox(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody List<Long> listIdPeopleCheckbox) {
        optionPeopleManagementService.moveAllPeopleByStatusAndPeopleIdAndCheckbox(status, listIdPeopleCheckbox);
        return new ResponseEntity<>(OK);
    }



}
