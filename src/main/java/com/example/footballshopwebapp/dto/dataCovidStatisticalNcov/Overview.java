package com.example.footballshopwebapp.dto.dataCovidStatisticalNcov;

import lombok.Data;

@Data
public class Overview {
    public String date;
    public int death;
    public int treating;
    public int cases;
    public int recovered;
    public int avgCases7day;
    public int avgRecovered7day;
    public int avgDeath7day;
}
