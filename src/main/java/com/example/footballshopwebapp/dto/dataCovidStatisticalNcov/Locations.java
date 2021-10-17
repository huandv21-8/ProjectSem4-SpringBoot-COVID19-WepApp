package com.example.footballshopwebapp.dto.dataCovidStatisticalNcov;

import lombok.Data;

@Data
public class Locations {
    public String name;
    public int death;
    public int treating;
    public int cases;
    public int recovered;
    public int casesToday;
}
