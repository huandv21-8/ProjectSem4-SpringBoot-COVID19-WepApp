package com.example.footballshopwebapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.footballshopwebapp.dto.Otp;
import com.example.footballshopwebapp.dto.SmsPojo;
import com.example.footballshopwebapp.dto.StoreOtp;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.mail.Store;

@Component
public class SmsService {
//    private final String ACCOUNT_SID = "AC1daebab2c3433315b05efcab26688340";
//
//    private final String AUTH_TOKEN = "d96aefad7b7897c72e2c8c30c69e9f77";
//
//    private final String FROM_NUMBER = "+15703619943";

//
//    private final String ACCOUNT_SID = "ACec76cbdb19b31d8d747439c756e04032";
//
//    private final String AUTH_TOKEN = "d389c98f15426b3978a4c54e386abb1e";
//
//    private final String FROM_NUMBER = "+13853991793";

    public void send(SmsPojo sms) throws ParseException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        int min = 100000;
        int max = 999999;
        int number = (int) (Math.random() * (max - min + 1) + min);


        String msg = "Your OTP - " + number + ". Please verify this OTP in the app";


       Message.creator(new PhoneNumber(sms.getPhone()), new PhoneNumber(FROM_NUMBER), msg)
                .create();

        StoreOtp.setOtp(number);

    }


    public Boolean verifyOtp(Otp otp) {
        if (otp.getOtp() == StoreOtp.getOtp()){
            return true;
        }else {
            return false;
        }
    }
}
