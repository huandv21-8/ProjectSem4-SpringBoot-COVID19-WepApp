package com.example.footballshopwebapp.share.helper;

import com.example.footballshopwebapp.exceptions.SpringException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateHelper {
    public Date changeTime(Date date) throws SpringException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String stringDate = simpleDateFormat.format(date);
            simpleDateFormat.applyPattern("dd/MM/yyyy HH:mm:ss");
            return simpleDateFormat.parse(stringDate);
        } catch (Exception e) {
            throw new SpringException("Lỗi chuyển đổi thời gian");
        }
    }

    public Date getDateNow() throws SpringException {
        try {
            return this.changeTime(new Date(new Date().getTime()));
        } catch (Exception e) {
            throw new SpringException("Lỗi lấy thời gian hiện tại");
        }
    }
}
