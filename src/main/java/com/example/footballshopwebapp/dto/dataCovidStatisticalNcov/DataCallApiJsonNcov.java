package com.example.footballshopwebapp.dto.dataCovidStatisticalNcov;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class DataCallApiJsonNcov {
    public Total total ;
    public Total today;
    public List<Overview> overview;
    public List<Locations> locations;
}
