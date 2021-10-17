package com.example.footballshopwebapp.dto.dataCovidStatisticalNcov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Common {
    public int death;
    public int treating;
    public int cases;
    public int recovered;
}
