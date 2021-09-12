package com.example.footballshopwebapp.dto.response;

public interface ICountPeopleByProvince {
    String getProvinceName();
    Long getCountPeople();
    Long getProvinceId();

    static CountPeopleByProvince get(ICountPeopleByProvince peopleByProvince) {
        return new CountPeopleByProvince(peopleByProvince.getProvinceId(),peopleByProvince.getProvinceName(), peopleByProvince.getCountPeople());
    }
}
