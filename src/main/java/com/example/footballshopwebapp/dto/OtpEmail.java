package com.example.footballshopwebapp.dto;

public class OtpEmail {
    private static int otp;

    public static int getOtp() {
        return otp;
    }

    public static void setOtp(int otp) {
        OtpEmail.otp = otp;
    }
}
