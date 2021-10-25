package com.example.footballshopwebapp.controller.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.footballshopwebapp.dto.Otp;
import com.example.footballshopwebapp.dto.SmsPojo;
import com.example.footballshopwebapp.service.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/sms")
public class SMSController {


    private final SmsService serviceSms;


    private SimpMessagingTemplate webSocket;

    private final String TOPIC_DESTINATION = "/lesson/sms";

    @PostMapping("/mobile")
    public ResponseEntity<Boolean> smsSubmit(@RequestBody @Validated SmsPojo sms) {
        try {
            serviceSms.send(sms.getPhone());
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: " + sms.getPhone());
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    @PostMapping("/verifyOtp")
    public Boolean verifyOtp(@RequestBody @Validated Otp otp) {
        return serviceSms.verifyOtp(otp);
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}