package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.request.ListPhoneRequest;
import com.example.footballshopwebapp.dto.response.*;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.entity.Question;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/declare")
public class DeclareManagementController {

    private final DeclareManagementService declareManagementService;


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

    @GetMapping(value = "/detailAccount")
    public ResponseEntity<AccountResponseByAll> detailAccount(@RequestParam(value = "accountId") Long accountId) {
        return status(HttpStatus.OK).body(declareManagementService.detailAccount(accountId));
    }

    @GetMapping(value = "/listAccount")
    public ResponseEntity<List<AccountResponseByAll>> listAccount() {
        return status(HttpStatus.OK).body(declareManagementService.listAccount());
    }

    @DeleteMapping(value = "/managementAccount/{accountId}")
    public ResponseEntity<Message> managementAccount(@PathVariable(value = "accountId") Long accountId,
                                                     @RequestParam(value = "optionChoose") String optionChoose) {
        return status(HttpStatus.OK).body(declareManagementService.managementAccount(optionChoose, accountId));
    }

    @PostMapping(value = "/managementAllAccount")
    public ResponseEntity<Message> managementAllAccountByCheckBox(@RequestParam(value = "optionChoose") String optionChoose,
                                                                  @RequestBody List<Long> listAccountIdCheckbox) {
        return status(HttpStatus.OK).body(
                declareManagementService.managementAllAccountByCheckBox(optionChoose, listAccountIdCheckbox));
    }

    @GetMapping("/listAccountSearch")
    public ResponseEntity<List<AccountResponseByAll>> listAccountSearch(
            @RequestParam(required = false, name = "phone") String phone,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "birthDay") String birthDay,
            @RequestParam(required = false, name = "provinceId") Long provinceId,
            @RequestParam(required = false, name = "fever") boolean fever,
            @RequestParam(required = false, name = "cough") boolean cough,
            @RequestParam(required = false, name = "shortnessOfBreath") boolean shortnessOfBreath,
            @RequestParam(required = false, name = "pneumonia") boolean pneumonia,
            @RequestParam(required = false, name = "soreThroat") boolean soreThroat,
            @RequestParam(required = false, name = "tired") boolean tired,
            @RequestParam(required = false, name = "exposureToF0") boolean exposureToF0
    ) {
        return status(HttpStatus.OK).body(declareManagementService.listAccountSearch(phone, name, birthDay, provinceId, fever, cough, shortnessOfBreath, pneumonia, soreThroat, tired, exposureToF0));
    }


    @PostMapping
    public ResponseEntity<QuestionResponse> declare(@RequestBody @Validated DeclareRequest declareRequest) {

        return status(HttpStatus.OK).body(declareManagementService.declare(declareRequest));
    }

    @GetMapping(value = "/listDeclare")
    public ResponseEntity<List<Question>> listDeclare() {
        return status(HttpStatus.OK).body(declareManagementService.listDeclare());
    }

    @GetMapping(value = "/listDeclareByAccountId")
    public ResponseEntity<List<DeclareResponse>> listDeclareByAccountId(@RequestParam(value = "accountId") Long accountId,
                                                                        @RequestParam(value = "orderByDate") String orderByDate) {
        return status(HttpStatus.OK).body(declareManagementService.listDeclareByAccountId(accountId, orderByDate));
    }

    @GetMapping(value = "/detailDeclare/{questionId}")   // lấy khai báo chi tiết theo id
    public ResponseEntity<QuestionResponse> detailDeclare(@PathVariable(value = "questionId") Long questionId) {
        return status(HttpStatus.OK).body(declareManagementService.detailDeclare(questionId));
    }

    @PostMapping(value = "/detailDeclareRecent") // lấy khai báo chi tiết theo phone
    public ResponseEntity<QuestionResponse> detailDeclareRecent(@PathParam(value = "phone") String phone) {
        return status(HttpStatus.OK).body(declareManagementService.detailDeclareRecent(phone));
    }


    @PostMapping(value = "/sendSmsListAccount")
    public ResponseEntity<Boolean> sendSmsListAccount(@RequestBody ListPhoneRequest phoneList) {
        return status(HttpStatus.OK).body(declareManagementService.sendSmsListAccount(phoneList));
    }
}
