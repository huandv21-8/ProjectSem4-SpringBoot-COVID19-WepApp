package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.dto.response.AccountResponseByAll;
import com.example.footballshopwebapp.entity.*;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.*;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import com.example.footballshopwebapp.share.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeclareManagementServiceImp implements DeclareManagementService {
    private final CommuneRepository communeRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AccountMapper accountMapper;
    private final DateHelper dateHelper;
    private final PeopleRepository peopleRepository;
    private final StatusByTimeRepository statusByTimeRepository;


    @Override
    @Transactional
    public Message declare(DeclareRequest declareRequest) {

        try {
            Question question = new Question();

            Account account = accountRepository.findByAccPhone(declareRequest.getPhone());
            if (account != null) {

                if (account.getCommune().getCommuneId() != declareRequest.getIdCommune()) {
                    Commune commune = communeRepository.findByCommuneId(declareRequest.getIdCommune());
                    account.setCommune(commune);
                    account = accountRepository.save(account);
                }
                question.setAccount(account);
            } else {
                Account account1 = new Account();
                account1.setBirthDay(declareRequest.getBirthDay());
                account1.setName(declareRequest.getName());
                account1.setCmt(declareRequest.getCmt());
                account1.setGender(declareRequest.isGender());
                account1.setPhone(declareRequest.getPhone());
                account1.setTime(dateHelper.getDateNow());
                account1.setActive(true);
                account1.setAddress(declareRequest.getAddress());
                Commune commune = communeRepository.findByCommuneId(declareRequest.getIdCommune());
                account1.setCommune(commune);
                question.setAccount(accountRepository.save(account1));
            }
            question.setExposureToF0(declareRequest.isExposureToF0());
            question.setComeBackFromEpidemicArea(declareRequest.isComeBackFromEpidemicArea());
            question.setContactWithPeopleReturningFromEpidemicAreas(declareRequest.isContactWithPeopleReturningFromEpidemicAreas());
            question.setFever(declareRequest.isFever());
            question.setCough(declareRequest.isCough());
            question.setShortnessOfBreath(declareRequest.isShortnessOfBreath());
            question.setPneumonia(declareRequest.isPneumonia());
            question.setSoreThroat(declareRequest.isSoreThroat());
            question.setTired(declareRequest.isTired());
            question.setChronicLiverDisease(declareRequest.isChronicLiverDisease());
            question.setChronicBloodDisease(declareRequest.isChronicBloodDisease());
            question.setChronicLungDisease(declareRequest.isChronicLungDisease());
            question.setChronicKideyDisease(declareRequest.isChronicKideyDisease());
            question.setHeartRelatedDiseaes(declareRequest.isHeartRelatedDiseaes());
            question.setHighBloodPressure(declareRequest.isHighBloodPressure());
            question.setHivOrImmunocompromised(declareRequest.isHivOrImmunocompromised());
            question.setOrganTransplantRecipient(declareRequest.isOrganTransplantRecipient());
            question.setDiabetes(declareRequest.isDiabetes());
            question.setCancer(declareRequest.isCancer());
            question.setPregnant(declareRequest.isPregnant());
            question.setTravelSchedule(declareRequest.getTravelSchedule());
            question.setCreatedAt(dateHelper.getDateNow());
            questionRepository.save(question);

            return new Message("Khai bao thanh cong");
        } catch (Exception e) {
            throw new SpringException("Loi roi : " + e.getMessage());
        }


    }

    @Override
    public AccountResponse findAccountByPhone(String phone) {
        Account account = new Account();
        account = accountRepository.findByAccPhone(phone);
        String birthDay = dateHelper.convertDateToString(account.getBirthDay(), "yyyy-MM-dd");

        return accountMapper.accountResponseMap(account, birthDay);
    }

    @Override
    public Message createAccount(AccountRequest accountRequest) {
        try {
            Account account = new Account();
            account.setBirthDay(accountRequest.getBirthDay());
            account.setName(accountRequest.getName());
            account.setCmt(accountRequest.getCmt());
            account.setGender(accountRequest.isGender());
            account.setPhone(accountRequest.getPhone());
            account.setTime(dateHelper.getDateNow());
            account.setActive(true);
            account.setAddress(accountRequest.getAddress());
            Commune commune = communeRepository.findByCommuneId(accountRequest.getIdCommune());
            account.setCommune(commune);
            accountRepository.save(account);
            return new Message("Thêm thành công");
        } catch (Exception e) {
            throw new SpringException("Loi roi : " + e.getMessage());
        }
    }

    @Override
    public List<Question> listDeclare() {
        return questionRepository.findAll();
    }

    @Override
    public Question detailDeclare(Long questionId) {
        Question question = questionRepository.findByQuestionId(questionId)
                .orElseThrow(() -> new SpringException("Khong ton tai question co id: " + questionId));
        return question;
    }

    @Override
    public Message updateAccount(AccountRequest accountRequest) {
        try {
            Account account = accountRepository.findByAccPhone(accountRequest.getPhone());
            account.setBirthDay(accountRequest.getBirthDay());
            account.setName(accountRequest.getName());
            account.setCmt(accountRequest.getCmt());
            account.setGender(accountRequest.isGender());
            account.setTime(dateHelper.getDateNow());
            account.setAddress(accountRequest.getAddress());
            Commune commune = communeRepository.findByCommuneId(accountRequest.getIdCommune());
            account.setCommune(commune);
            accountRepository.save(account);
            return new Message("Cập nhật thành công");
        } catch (Exception e) {
            throw new SpringException("Loi roi : " + e.getMessage());
        }
    }

    @Override
    public List<AccountResponseByAll> listAccount() {
        return accountRepository.findAllByActiveTrue().stream().map((item) -> {
            String birthDay = dateHelper.convertDateToString(item.getBirthDay(), "dd/MM/yyyy");
            return accountMapper.accountResponseByAllMap(item, birthDay);
        }).collect(Collectors.toList());
    }

    @Override
    public Message managementAccount(String optionChoose, Long accountId) {
        try {
            Account account = accountRepository.findAccountByAccountId(accountId);
            if (optionChoose.equals("deleteAccount") || optionChoose.equals("deleteAllAccount")) {
                account.setActive(false);
                accountRepository.save(account);
            } else {
                People people = peopleRepository.findPeople(account.getBirthDay(),
                        account.getCmt(),
                        account.isGender(),
                        account.getName(),
                        account.getPhone(),
                        account.getCommune().getCommuneId());
                if (!Objects.isNull(people)
                        && people.getName().equals(account.getName())
                        && people.getCommune().equals(account.getCommune())
                        && people.getBirthDay().equals(account.getBirthDay())) {

                    StatusByTime statusByTime = new StatusByTime();
                    statusByTime.setPeople(people);
                    statusByTime.setType(true);
                    statusByTime.setUpdatedAt(dateHelper.getDateNow());

                    if (optionChoose.equals("sickAccount") || optionChoose.equals("sickAllAccount")) {
                        statusByTime.setStatus(VariableCommon.SICK);
                    }
                    if (optionChoose.equals("f1Account") || optionChoose.equals("f1AllAccount")) {
                        statusByTime.setStatus(VariableCommon.F1);
                    }
                    statusByTimeRepository.save(statusByTime);
                } else {
                    People people1 = new People();
                    people1.setName(account.getName());
                    people1.setBirthDay(account.getBirthDay());
                    people1.setGender(account.isGender());
                    people1.setPhone(account.getPhone());
                    people1.setCommune(communeRepository.findByCommuneId(account.getCommune().getCommuneId()));
                    people1.setTime(dateHelper.getDateNow());
                    people1.setCmt(account.getCmt());
                    people1.setActive(true);

                    StatusByTime statusByTime = new StatusByTime();
                    statusByTime.setPeople(peopleRepository.save(people1));
                    if (optionChoose.equals("sickAccount") || optionChoose.equals("sickAllAccount")) {
                        statusByTime.setStatus(VariableCommon.SICK);
                    }
                    if (optionChoose.equals("f1Account") || optionChoose.equals("f1AllAccount")) {
                        statusByTime.setStatus(VariableCommon.F1);
                    }
                    statusByTime.setType(true);
                    statusByTime.setUpdatedAt(dateHelper.getDateNow());
                    statusByTimeRepository.save(statusByTime);
                }

            }
            return new Message("Thành công");
        } catch (Exception e) {
            throw new SpringException("Loi roi : " + e.getMessage());
        }
    }

    @Override
    public Message managementAllAccountByCheckBox(String optionChoose, List<Long> listAccountIdCheckbox) {
        if (listAccountIdCheckbox.size() > 0) {
            listAccountIdCheckbox.stream().forEach(item -> {
                managementAccount(optionChoose, item);
            });
            return new Message("Xóa thành công");
        } else {
            throw new SpringException("Loi roi ");
        }

    }


}
