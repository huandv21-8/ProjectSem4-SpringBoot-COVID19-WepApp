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

    @PostMapping("moveCuredPeopleById")
    public ResponseEntity<Void> moveCuredPeopleById(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody Long idChoicePeople) {
        optionPeopleManagementService.moveCuredPeopleById(status, idChoicePeople);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveCuredAllPeopleByCheckbox")
    public ResponseEntity<Void> moveCuredAllPeopleByCheckbox(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody List<Long> listIdPeopleCheckbox) {
        optionPeopleManagementService.moveCuredAllPeopleByCheckbox(status, listIdPeopleCheckbox);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveF1PeopleById")
    public ResponseEntity<Void> moveF1PeopleById(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody Long idChoicePeople) {
        optionPeopleManagementService.moveF1PeopleById(status, idChoicePeople);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveF1AllPeopleByCheckbox")
    public ResponseEntity<Void> moveF1AllPeopleByCheckbox(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody List<Long> listIdPeopleCheckbox) {
        optionPeopleManagementService.moveF1AllPeopleByCheckbox(status, listIdPeopleCheckbox);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveDiedPeopleById")
    public ResponseEntity<Void> moveDiedPeopleById(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody Long idChoicePeople) {
        optionPeopleManagementService.moveDiedPeopleById(status, idChoicePeople);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveDiedAllPeopleByCheckbox")
    public ResponseEntity<Void> moveDiedAllPeopleByCheckbox(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody List<Long> listIdPeopleCheckbox) {
        optionPeopleManagementService.moveDiedAllPeopleByCheckbox(status, listIdPeopleCheckbox);
        return new ResponseEntity<>(OK);
    }
    @PostMapping("moveSickPeopleById")
    public ResponseEntity<Void> moveSickPeopleById(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody Long idChoicePeople) {
        optionPeopleManagementService.moveSickPeopleById(status, idChoicePeople);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("moveSickAllPeopleByCheckbox")
    public ResponseEntity<Void> moveSickAllPeopleByCheckbox(
            @RequestParam(required = false, name = "status") String status,
            @RequestBody List<Long> listIdPeopleCheckbox) {
        optionPeopleManagementService.moveSickAllPeopleByCheckbox(status, listIdPeopleCheckbox);
        return new ResponseEntity<>(OK);
    }


}
