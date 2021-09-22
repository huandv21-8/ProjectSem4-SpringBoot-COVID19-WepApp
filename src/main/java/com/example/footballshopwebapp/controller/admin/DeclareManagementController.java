package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.entity.Question;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/findAccountByPhone")
    public ResponseEntity<AccountResponse> findAccountByPhone(@RequestParam(value = "phone") String phone) {
        return status(HttpStatus.OK).body(declareManagementService.findAccountByPhone(phone));
    }

    @PostMapping(value = "/createAccount")
    public ResponseEntity<Message> createAccount(@RequestBody @Validated AccountRequest accountRequest) {

        return status(HttpStatus.OK).body(declareManagementService.createAccount(accountRequest));
    }
    @PostMapping(value = "/updateAccount")
    public ResponseEntity<Message> updateAccount(@RequestBody @Validated AccountRequest accountRequest) {

        return status(HttpStatus.OK).body(declareManagementService.updateAccount(accountRequest));
    }

    @GetMapping(value = "/listDeclare")
    public ResponseEntity<List<Question>> listDeclare() {
        return status(HttpStatus.OK).body(declareManagementService.listDeclare());
    }
    @GetMapping(value = "/detailDeclare/{questionId}")
    public ResponseEntity<Question> detailDeclare(@PathVariable(value = "questionId") Long questionId) {
        return status(HttpStatus.OK).body(declareManagementService.detailDeclare(questionId));
    }


}
