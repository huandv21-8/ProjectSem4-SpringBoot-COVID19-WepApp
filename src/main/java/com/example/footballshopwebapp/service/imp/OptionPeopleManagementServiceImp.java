package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.entity.Cured;
import com.example.footballshopwebapp.entity.Died;
import com.example.footballshopwebapp.entity.F1;
import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.repository.*;
import com.example.footballshopwebapp.service.OptionPeopleManagementService;
import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionPeopleManagementServiceImp implements OptionPeopleManagementService {
    private final SickRepository sickRepository;
    private final F1Repository f1Repository;
    private final CuredRepository curedRepository;
    private final DiedRepository diedRepository;
    private final DateHelper dateHelper;

    private Sick setSickByIdFind(Long id, String status) {
        Sick sick = sickRepository.findBySickId(id);
        Sick moveCured = new Sick();
        moveCured.setPeople(sick.getPeople());
        moveCured.setActive(true);
        moveCured.setStatus(status);
        moveCured.setTime(dateHelper.getDateNow());
        moveCured.setType(sick.isType());
        if (sick.isType()) {
            moveCured.setIdSickSource(sick.getIdSickSource());
        }
        return sickRepository.save(moveCured);
    }

    @Override
    @Transactional
    public void moveCuredPeopleById(String status, Long idChoicePeople) {
        Sick sick = setSickByIdFind(idChoicePeople, VariableCommon.CURED);

        Cured cured = new Cured();
        cured.setPeople(sick.getPeople());
        cured.setActive(true);
        cured.setStatus(sick.getStatus());
        cured.setTime(dateHelper.getDateNow());
        cured.setType(sick.isType());
        if (sick.isType()) {
            cured.setIdSickSource(sick.getIdSickSource());
        }

        curedRepository.save(cured);
    }

    @Override
    public void moveCuredAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        for (Long id : listIdPeopleCheckbox) {
            moveCuredPeopleById(status, id);
        }
    }

    @Override
    @Transactional
    public void moveF1PeopleById(String status, Long idChoicePeople) {
        F1 f1 = new F1();
        if (status.equals(VariableCommon.SICK)) {
            Sick sick = setSickByIdFind(idChoicePeople, VariableCommon.F1);

            f1.setPeople(sick.getPeople());
            f1.setActive(sick.isActive());
            f1.setStatus(sick.getStatus());
            f1.setTime(dateHelper.getDateNow());
            f1.setType(sick.isType());
            if (sick.isType()) {
                f1.setSickSource(sickRepository.findBySickId(sick.getIdSickSource()));
            }
            f1Repository.save(f1);
        }

        if (status.equals(VariableCommon.CURED)) {
            Cured cured = curedRepository.findByCuredId(idChoicePeople);
            Cured cured1 = new Cured();
            cured1.setPeople(cured.getPeople());
            cured1.setActive(true);
            cured1.setStatus(VariableCommon.F1);
            cured1.setTime(dateHelper.getDateNow());
            cured1.setType(cured.isType());
            if (cured.isType()) {
                cured1.setIdSickSource(cured.getIdSickSource());
            }
            curedRepository.save(cured1);

            f1.setPeople(cured1.getPeople());
            f1.setActive(cured1.isActive());
            f1.setStatus(cured1.getStatus());
            f1.setTime(dateHelper.getDateNow());
            f1.setType(cured1.isType());
            if (cured1.isType()) {
                f1.setSickSource(sickRepository.findBySickId(cured1.getIdSickSource()));
            }
            f1Repository.save(f1);

        }


    }

    @Override
    public void moveF1AllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        for (Long id : listIdPeopleCheckbox) {
            moveF1PeopleById(status, id);
        }
    }

    @Override
    public void moveDiedPeopleById(String status, Long idChoicePeople) {
        Sick sick = setSickByIdFind(idChoicePeople, VariableCommon.CURED);
        Died died = new Died();
        died.setSick(sick);
        died.setTime(dateHelper.getDateNow());
        died.setActive(true);
        diedRepository.save(died);
    }

    @Override
    public void moveDiedAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        for (Long id : listIdPeopleCheckbox) {
            moveDiedPeopleById(status, id);
        }
    }

    @Override
    public void moveSickPeopleById(String status, Long idChoicePeople) {
        if (status.equals(VariableCommon.CURED)) {
            Cured cured = curedRepository.findByCuredId(idChoicePeople);
            Cured cured1 = new Cured();
            cured1.setPeople(cured.getPeople());
            cured1.setActive(true);
            cured1.setStatus(VariableCommon.SICK);
            cured1.setTime(dateHelper.getDateNow());
            cured1.setType(cured.isType());
            if (cured.isType()) {
                cured1.setIdSickSource(cured.getIdSickSource());
            }
            curedRepository.save(cured1);

            Sick moveSick = new Sick();
            moveSick.setPeople(cured1.getPeople());
            moveSick.setActive(cured1.isActive());
            moveSick.setStatus(cured1.getStatus());
            moveSick.setTime(dateHelper.getDateNow());
            moveSick.setType(cured1.isType());
            if (cured1.isType()) {
                moveSick.setIdSickSource(cured1.getIdSickSource());
            }
            sickRepository.save(moveSick);

        }

        if (status.equals(VariableCommon.F1)) {
            F1 f1 = f1Repository.findByF1Id(idChoicePeople);
            F1 sick = new F1();
            sick.setPeople(f1.getPeople());
            sick.setActive(f1.isActive());
            sick.setStatus(VariableCommon.SICK);
            sick.setTime(dateHelper.getDateNow());
            sick.setType(f1.isType());
            if (f1.isType()) {
                sick.setSickSource(f1.getSickSource());
            }

            f1Repository.save(sick);

            Sick moveSick = new Sick();
            moveSick.setPeople(sick.getPeople());
            moveSick.setActive(sick.isActive());
            moveSick.setStatus(sick.getStatus());
            moveSick.setTime(dateHelper.getDateNow());
            moveSick.setType(sick.isType());
            if (sick.isType()) {
                moveSick.setIdSickSource(sick.getSickSource().getIdSickSource());
            }
            sickRepository.save(moveSick);
        }

    }

    @Override
    public void moveSickAllPeopleByCheckbox(String status, List<Long> listIdPeopleCheckbox) {
        System.out.println(status);
        System.out.println(listIdPeopleCheckbox.size());
        for (Long id : listIdPeopleCheckbox) {
            moveSickPeopleById(status, id);
        }
    }


}
