package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.dto.response.PeopleDetailResponseAdmin;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/declare")
public class DeclareManagementController {

    private  final DeclareManagementService declareManagementService;
    @PostMapping
    public ResponseEntity<Message> declare(@RequestBody @Validated DeclareRequest declareRequest){

        return status(HttpStatus.OK).body(declareManagementService.declare(declareRequest));
    }

}
