package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.dataCovidStatisticalNcov.*;
import com.example.footballshopwebapp.dto.response.CountPeopleByProvince;

import com.example.footballshopwebapp.dto.response.ICountPeopleByProvince;
import com.example.footballshopwebapp.entity.StatusByTime;
import com.example.footballshopwebapp.repository.StatusByTimeRepository;
import com.example.footballshopwebapp.service.StatisticalPeopleService;

import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import lombok.AllArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@AllArgsConstructor
public class StatisticalPeopleServiceImp implements StatisticalPeopleService {

    private final DateHelper dateHelper;
    private final StatusByTimeRepository statusByTimeRepository;


    @Override
    public Map<String, TreeMap<Date, Integer>> dashboard(String timeForm) {
        Map<String, TreeMap<Date, Integer>> mapByStatus = null;
        if (timeForm != null) {
            mapByStatus = new HashMap<>();
            TreeMap<Date, Integer> mapCuredByTime = new TreeMap<>();
            TreeMap<Date, Integer> mapSickByTime = new TreeMap<>();
            TreeMap<Date, Integer> mapDiedByTime = new TreeMap<>();
            List<Date> listDate;

            List<StatusByTime> curedList = new ArrayList<>();
            List<StatusByTime> diedList = new ArrayList<>();
            List<StatusByTime> sickList = new ArrayList<>();

            String formTime;

            if (timeForm.equals(VariableCommon.DAY)) {
                listDate = dateHelper.getDatesBetweenDay(Calendar.DAY_OF_MONTH, 30);
                formTime = "dd/MM/yyyy";
            } else if (timeForm.equals(VariableCommon.MONTH)) {
                listDate = dateHelper.getDatesBetweenDay(Calendar.MONTH, 12);
                formTime = "MM/YYYY";
            } else {
                listDate = dateHelper.getDatesBetweenDay(Calendar.YEAR, 3);
                formTime = "YYYY";
            }

            listDate.forEach(time -> {

                String strTime = dateHelper.convertDateToString(time, formTime);

                for (StatusByTime sick : statusByTimeRepository.findAllByStatusAndActiveTrue(VariableCommon.SICK)) {
                    if (strTime.equals(dateHelper.convertDateToString(sick.getUpdatedAt(), formTime))) {
                        sickList.add(sick);
                    }
                }
                mapSickByTime.put(time, sickList.size());
                sickList.clear();

                for (StatusByTime died : statusByTimeRepository.findAllByStatusAndActiveTrue(VariableCommon.DIED)) {
                    if (strTime.equals(dateHelper.convertDateToString(died.getUpdatedAt(), formTime))) {
                        diedList.add(died);
                    }
                }
                mapDiedByTime.put(time, diedList.size());
                diedList.clear();

                for (StatusByTime cured : statusByTimeRepository.findAllByStatusAndActiveTrue(VariableCommon.CURED)) {
                    if (strTime.equals(dateHelper.convertDateToString(cured.getUpdatedAt(), formTime))) {
                        curedList.add(cured);
                    }
                }
                mapCuredByTime.put(time, curedList.size());
                curedList.clear();

            });

            mapByStatus.put(VariableCommon.CURED, mapCuredByTime);
            mapByStatus.put(VariableCommon.SICK, mapSickByTime);
            mapByStatus.put(VariableCommon.DIED, mapDiedByTime);
        }
        return mapByStatus;
    }

    @Override
    public List<CountPeopleByProvince> countPeopleByStatusAboutProvince() {
        List<ICountPeopleByProvince> iCountCuredByProvinces = statusByTimeRepository.listCountPeopleByProvince(VariableCommon.CURED);
        List<ICountPeopleByProvince> iCountDiedByProvinces = statusByTimeRepository.listCountPeopleByProvince(VariableCommon.DIED);
        List<ICountPeopleByProvince> iCountSickByProvinces = statusByTimeRepository.listCountPeopleByProvince(VariableCommon.SICK);

        List<CountPeopleByProvince> countPeopleByProvinces = new ArrayList<>();

        iCountCuredByProvinces.stream().forEach(cured -> {
            CountPeopleByProvince countPeopleByProvince = new CountPeopleByProvince();
            countPeopleByProvince.setCountPeopleCured(cured.getCountPeople());
            countPeopleByProvince.setProvinceId(cured.getProvinceId());
            countPeopleByProvince.setProvinceName(cured.getProvinceName());
            iCountDiedByProvinces.stream().forEach(died -> {
                if (died.getProvinceId() == cured.getProvinceId()) {
                    countPeopleByProvince.setCountPeopleDied(died.getCountPeople());
                }
            });
            iCountSickByProvinces.stream().forEach(sick -> {
                if (sick.getProvinceId() == cured.getProvinceId()) {
                    countPeopleByProvince.setCountPeopleSick(sick.getCountPeople());
                }
            });
            countPeopleByProvinces.add(countPeopleByProvince);
        });
        return countPeopleByProvinces;
    }

    @Override
    public DataCallApiJsonNcov testDataCovid() throws Exception {
        DataCallApiJsonNcov dataCallApiJsonNcov = getDataFileJsonNcov();
        return dataCallApiJsonNcov;
    }


    public DataCallApiJsonNcov getDataFileJsonNcov() throws Exception {
        DataCallApiJsonNcov dataCallApiJsonNcov = new DataCallApiJsonNcov();
        String rawJson = request("https://static.pipezero.com/covid/data.json");
        JSONObject root = new JSONObject(rawJson);

        // TOTAL
        JSONObject total = root.getJSONObject("total");
        JSONObject totalInternal = total.getJSONObject("internal");
        Common totalInternalCommon = new Common(totalInternal.getInt("death"), totalInternal.getInt("treating"), totalInternal.getInt("cases"), totalInternal.getInt("recovered"));
        JSONObject totalWorld = total.getJSONObject("world");
        Common totalWorldCommon = new Common(totalWorld.getInt("death"), totalWorld.getInt("treating"), totalWorld.getInt("cases"), totalWorld.getInt("recovered"));
        Total totalDTO = new Total(totalInternalCommon, totalWorldCommon);
        dataCallApiJsonNcov.setTotal(totalDTO);

        // TODAY
        JSONObject today = root.getJSONObject("today");
        JSONObject todayInternal = today.getJSONObject("internal");
        Common todayInternalCommon = new Common(todayInternal.getInt("death"), todayInternal.getInt("treating"), todayInternal.getInt("cases"), todayInternal.getInt("recovered"));
        JSONObject todayWorld = today.getJSONObject("world");
        Common todayWorldCommon = new Common(todayWorld.getInt("death"), todayWorld.getInt("treating"), todayWorld.getInt("cases"), todayWorld.getInt("recovered"));
        Total todayDTO = new Total(totalInternalCommon, totalWorldCommon);
        dataCallApiJsonNcov.setToday(todayDTO);

        // OVERVIEW
        List<Overview> overviewList = new ArrayList<>();
        JSONArray overview = root.getJSONArray("overview");
        for (int i = 0; i < overview.length(); i++) {
            JSONObject jsonOverview = overview.getJSONObject(i);
            Overview overviewDTO = new Overview();
            overviewDTO.setDate(jsonOverview.getString("date"));
            overviewDTO.setDeath(jsonOverview.getInt("death"));
            overviewDTO.setTreating(jsonOverview.getInt("treating"));
            overviewDTO.setCases(jsonOverview.getInt("cases"));
            overviewDTO.setRecovered(jsonOverview.getInt("recovered"));
            overviewDTO.setAvgCases7day(jsonOverview.getInt("avgCases7day"));
            overviewDTO.setAvgRecovered7day(jsonOverview.getInt("avgRecovered7day"));
            overviewDTO.setAvgDeath7day(jsonOverview.getInt("avgDeath7day"));
            overviewList.add(overviewDTO);
        }
        dataCallApiJsonNcov.setOverview(overviewList);


        // LOCATIONS
        List<Locations> locationsList = new ArrayList<>();
        JSONArray locations = root.getJSONArray("locations");
        for (int i = 0; i < locations.length(); i++) {
            JSONObject jsonLocation = locations.getJSONObject(i);
            Locations locationsDTO = new Locations();
            locationsDTO.setName(jsonLocation.getString("name"));
            locationsDTO.setDeath(jsonLocation.getInt("death"));
            locationsDTO.setTreating(jsonLocation.getInt("treating"));
            locationsDTO.setCases(jsonLocation.getInt("cases"));
            locationsDTO.setRecovered(jsonLocation.getInt("recovered"));
            locationsDTO.setCasesToday(jsonLocation.getInt("casesToday"));
        }
        dataCallApiJsonNcov.setLocations(locationsList);

        return dataCallApiJsonNcov;
    }


    public String request(String endpoint) throws Exception {
        StringBuilder sb = new StringBuilder();

        URL url = new URL(endpoint);

        // open a connection to this URL
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // read in the bytes
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // read them as characters.
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // read one line at a time.
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                // add this to our output
                sb.append(inputLine);
                // reading the next line
                inputLine = bufferedReader.readLine();
            }
        } finally {
            urlConnection.disconnect();
        }
        return sb.toString();

    }
}
