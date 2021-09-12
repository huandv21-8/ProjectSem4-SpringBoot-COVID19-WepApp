package com.example.footballshopwebapp.dto.response;


public class CountPeopleByProvince {
    private Long provinceId;
    private String provinceName;
    private Long countPeople;

    public CountPeopleByProvince() {}

    public CountPeopleByProvince(Long provinceId,String provinceName, Long countPeople) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.countPeople = countPeople;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(Long countPeople) {
        this.countPeople = countPeople;
    }
}
