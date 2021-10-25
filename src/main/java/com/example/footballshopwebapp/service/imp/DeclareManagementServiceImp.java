package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.request.ListPhoneRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.dto.response.AccountResponseByAll;
import com.example.footballshopwebapp.dto.response.DeclareResponse;
import com.example.footballshopwebapp.dto.response.QuestionResponse;
import com.example.footballshopwebapp.entity.*;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.*;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.service.SmsService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.helper.VariableCommon;
import com.example.footballshopwebapp.share.mapper.AccountMapper;
import com.example.footballshopwebapp.share.mapper.DeclareMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
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
    private final DeclareMapper declareMapper;
    private final SmsService serviceSms;

    @Override
    @Transactional
    public QuestionResponse declare(DeclareRequest declareRequest) {

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
            Question questionResponse = questionRepository.save(question);

            String birthDay = dateHelper.convertDateToString(questionResponse.getAccount().getBirthDay(), "dd/MM/yyyy");
            String updateAt = dateHelper.convertDateToString(questionResponse.getCreatedAt(), "dd/MM/yyyy hh:mm:ss");
            return declareMapper.questionResponseMap(questionResponse, birthDay, updateAt);
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
    public QuestionResponse detailDeclare(Long questionId) {
        Question question = questionRepository.findByQuestionId(questionId)
                .orElseThrow(() -> new SpringException("Khong ton tai question co id: " + questionId));
        String birthDay = dateHelper.convertDateToString(question.getAccount().getBirthDay(), "dd/MM/yyyy");
        String updateAt = dateHelper.convertDateToString(question.getCreatedAt(), "dd/MM/yyyy hh:mm:ss");
        return declareMapper.questionResponseMap(question, birthDay, updateAt);
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

    private List<AccountResponseByAll> convertQuestionToAccountResponseAndSort(List<Question> questionList) {
        List<AccountResponseByAll> accountResponseByAllList = new ArrayList<>();
        if (questionList.size() > 0) {
            questionList.stream().forEach(item -> {
                int ratio = 0;
                if (item.isExposureToF0()) {
                    ratio += 3;
                }
                if (item.isComeBackFromEpidemicArea()) {
                    ratio += 1;
                }
                if (item.isFever()) {
                    ratio += 1;
                }
                if (item.isCough()) {
                    ratio += 1;
                }
                if (item.isShortnessOfBreath()) {
                    ratio += 1;
                }
                if (item.isPneumonia()) {
                    ratio += 1;
                }
                if (item.isSoreThroat()) {
                    ratio += 1;
                }
                if (item.isTired()) {
                    ratio += 1;
                }
                String birthDay = dateHelper.convertDateToString(item.getAccount().getBirthDay(), "MM/dd/yyyy");
                String dateDeclare = dateHelper.convertDateToString(item.getCreatedAt(), "MM/dd/yyyy");
                AccountResponseByAll accountResponseByAll = accountMapper.accountResponseByAllMap(item.getAccount(), birthDay, dateDeclare);
                accountResponseByAll.setRatio(ratio);
                accountResponseByAllList.add(accountResponseByAll);
            });
            Collections.sort(accountResponseByAllList, new Comparator<AccountResponseByAll>() {
                @Override
                public int compare(AccountResponseByAll o1, AccountResponseByAll o2) {
                    if (o1.getRatio() > o2.getRatio()) {                    //so sanh điểm và sắp xếp theo chiều giam dần.
                        return -1;
                    }
                    return 1;
                }
            });
        }
        return accountResponseByAllList;
    }


    @Override
    public List<AccountResponseByAll> listAccount() {
        List<Question> questionList = questionRepository.listQuestionRecent().stream()
                .filter(item -> {
                    LocalDate dateLastFourteen = LocalDate.now().minusDays(14);
                    LocalDate date1 = item.getCreatedAt().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return dateLastFourteen.isBefore(date1);
                }).collect(Collectors.toList());
        return convertQuestionToAccountResponseAndSort(questionList);
    }

    @Override
    public Message managementAccount(String optionChoose, Long accountId) {
        try {
            Account account = accountRepository.findAccountByAccountId(accountId);
            if (optionChoose.equals("deleteAccount") || optionChoose.equals("deleteAllAccount")) {
                account.setActive(false);
                accountRepository.save(account);
            } else if (optionChoose.equals("sickAccount") || optionChoose.equals("sickAllAccount")
                    || optionChoose.equals("f1Account") || optionChoose.equals("f1AllAccount")) {
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
            if (optionChoose.equals("smsAllAccount") || optionChoose.equals("smsAccount")) {
                String phone = "+84" + account.getPhone();
                serviceSms.sendSmsListPhone(phone);
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

    @Override
    public AccountResponseByAll detailAccount(Long accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId);
        String birthDay = dateHelper.convertDateToString(account.getBirthDay(), "MM/dd/yyyy");
        String dateDeclare = dateHelper.convertDateToString(account.getBirthDay(), "MM/dd/yyyy");
        return accountMapper.accountResponseByAllMap(account, birthDay, dateDeclare);
    }

    @Override
    public List<AccountResponseByAll> listAccountSearch(String phone, String name, String birthDay, Long provinceId,
                                                        boolean fever,
                                                        boolean cough,
                                                        boolean shortnessOfBreath,
                                                        boolean pneumonia,
                                                        boolean soreThroat,
                                                        boolean tired,
                                                        boolean exposureToF0) {
        if (birthDay == null) {
            birthDay = "";
        }

        List<Question> questionList = questionRepository.findAllAccountSearch(name, birthDay, phone);

        List<Question> accountListAfterFilterByProvince = questionList.stream()
                .filter(item -> {
                    if (provinceId == null || provinceId == 0) {
                        return true;
                    }
                    return (item.getAccount().getCommune().getDistrict().getProvince().getProvinceId() == provinceId);
                })
                .filter(item -> {
                    LocalDate dateLastFourteen = LocalDate.now().minusDays(14);
                    LocalDate date1 = item.getCreatedAt().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return dateLastFourteen.isBefore(date1);
                }).collect(Collectors.toList());

        List<Question> accountListAfterFilterByAll = new ArrayList<>();
        accountListAfterFilterByProvince.forEach(item -> {
            if (fever && item.isFever()) {
                accountListAfterFilterByAll.add(item);
            }
            if (cough && item.isCough()) {
                if (!accountListAfterFilterByAll.contains(item)) {
                    accountListAfterFilterByAll.add(item);
                }
            }
            if (shortnessOfBreath && item.isShortnessOfBreath()) {
                if (!accountListAfterFilterByAll.contains(item)) {
                    accountListAfterFilterByAll.add(item);
                }
            }
            if (pneumonia && item.isPneumonia()) {
                if (!accountListAfterFilterByAll.contains(item)) {
                    accountListAfterFilterByAll.add(item);
                }
            }
            if (soreThroat && item.isSoreThroat()) {
                if (!accountListAfterFilterByAll.contains(item)) {
                    accountListAfterFilterByAll.add(item);
                }
            }
            if (tired && item.isTired()) {
                if (!accountListAfterFilterByAll.contains(item)) {
                    accountListAfterFilterByAll.add(item);
                }
            }
            if (exposureToF0 && item.isExposureToF0()) {
                if (!accountListAfterFilterByAll.contains(item)) {
                    accountListAfterFilterByAll.add(item);
                }
            }
        });
        if (!exposureToF0 && !tired && !soreThroat && !pneumonia && !shortnessOfBreath && !cough && !fever) {
            return convertQuestionToAccountResponseAndSort(accountListAfterFilterByProvince);
        }
        return convertQuestionToAccountResponseAndSort(accountListAfterFilterByAll);
    }

    @Override
    public List<DeclareResponse> listDeclareByAccountId(Long accountId, String orderByDate) {
        List<Question> listQuestion = new ArrayList<>();
        if (orderByDate.equals("ASC")) {
            listQuestion = questionRepository.findAllByAccountIdAndOrderByCreatedAtAsc(accountId);
        }
        if (orderByDate.equals("DESC")) {
            listQuestion = questionRepository.findAllByAccountIdAndOrderByCreatedAtDesc(accountId);
        }


        List<DeclareResponse> list = listQuestion.stream()
                .map(item -> {
                    String updateAt = dateHelper.convertDateToString(item.getCreatedAt(), "dd/MM/yyyy hh:mm:ss");
                    return declareMapper.accountResponseMap(item, updateAt);
                }).collect(Collectors.toList());
        return list;
    }

    @Override
    public QuestionResponse detailDeclareRecent(String phone) {
        Question question = questionRepository.detailDeclareRecent(phone);
        if (!Objects.isNull(question)) {
            String birthDay = dateHelper.convertDateToString(question.getAccount().getBirthDay(), "dd/MM/yyyy");
            String updateAt = dateHelper.convertDateToString(question.getCreatedAt(), "dd/MM/yyyy hh:mm:ss");
            return declareMapper.questionResponseMap(question, birthDay, updateAt);
        } else {
            throw new SpringException("khong tim thay ai!!!");
        }
    }

    @Override
    public Boolean sendSmsListAccount(ListPhoneRequest phoneList) {

        for (String phone : phoneList.getPhoneList()) {
            try {
                serviceSms.sendSmsListPhone(phone);
            } catch (Exception e) {

            }

        }
        return true;
    }


}
