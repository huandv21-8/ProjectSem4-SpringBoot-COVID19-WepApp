package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.AccountRequest;
import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.dto.response.AccountResponse;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.entity.Commune;
import com.example.footballshopwebapp.entity.Question;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.AccountRepository;
import com.example.footballshopwebapp.repository.CommuneRepository;
import com.example.footballshopwebapp.repository.QuestionRepository;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.DateHelper;
import com.example.footballshopwebapp.share.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DeclareManagementServiceImp implements DeclareManagementService {
    private final CommuneRepository communeRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AccountMapper accountMapper;
    private final DateHelper dateHelper;

    @Override
    @Transactional
    public Message declare(DeclareRequest declareRequest) {

        try {
            Account account = new Account();
            account.setBirthDay(declareRequest.getBirthDay());
            account.setName(declareRequest.getName());
            account.setCmt(declareRequest.getCmt());
            account.setGender(declareRequest.isGender());
            account.setPhone(declareRequest.getPhone());
            account.setTime(dateHelper.getDateNow());
            account.setAddress(declareRequest.getAddress());
            Commune commune = communeRepository.findByCommuneId(declareRequest.getIdCommune());
            account.setCommune(commune);


            Question question = new Question();
            question.setAccount(accountRepository.save(account));
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
        String birthDay= dateHelper.convertDateToString(account.getBirthDay(), "yyyy-MM-dd");

        return accountMapper.accountResponseMap(account,birthDay);
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
            account.setAddress(accountRequest.getAddress());
            Commune commune = communeRepository.findByCommuneId(accountRequest.getIdCommune());
            account.setCommune(commune);
            accountRepository.save(account);
            return new Message("Khai bao thanh cong");
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
               .orElseThrow(()->new SpringException("Khong ton tai question co id: " + questionId));
        return question;
    }


}
