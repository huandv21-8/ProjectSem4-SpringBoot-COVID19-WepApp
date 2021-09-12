package com.example.footballshopwebapp.dto;

import lombok.Data;


public class StoreOtp {
    private static int otp;

    public static int getOtp() {
        return otp;
    }

    public static void setOtp(int otp) {
        StoreOtp.otp = otp;
    }
}
