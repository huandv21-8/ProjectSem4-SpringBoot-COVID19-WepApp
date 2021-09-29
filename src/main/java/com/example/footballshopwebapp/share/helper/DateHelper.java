package com.example.footballshopwebapp.share.helper;

import com.example.footballshopwebapp.exceptions.SpringException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class DateHelper {
    public Date changeTime(Date date) throws SpringException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String stringDate = simpleDateFormat.format(date);
            simpleDateFormat.applyPattern("dd/MM/yyyy HH:mm:ss");
            return simpleDateFormat.parse(stringDate);
        } catch (Exception e) {
            throw new SpringException("Lỗi chuyển đổi thời gian");
        }
    }

    public Date formatDate(Date date, String form) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(form);
        String stringDate = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern(form);
        return simpleDateFormat.parse(stringDate);
    }


    public Date getDateNow() throws SpringException {
        try {
            return this.changeTime(new Date(new Date().getTime()));
        } catch (Exception e) {
            throw new SpringException("Lỗi lấy thời gian hiện tại");
        }
    }

    public List<Date> getDatesBetweenDay(int formTime , int numberDay) {
        Date endDate = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(endDate);
        cal.add(formTime, -numberDay);
        Date startDate = cal.getTime();

        List<Date> datesInRange = new ArrayList<>();

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        while (startCalendar.before(endCalendar)) {
            Date result = startCalendar.getTime();
            try {
                datesInRange.add(this.formatDate(result, "dd/MM/yyyy"));
            } catch (Exception e) {
                throw new SpringException("Lỗi chuyển đổi thời gian");
            }
            startCalendar.add(formTime, 1);
        }
        return datesInRange;
    }

    public String convertDateToString(Date date, String form) {
        DateFormat dateFormat = new SimpleDateFormat(form);
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
