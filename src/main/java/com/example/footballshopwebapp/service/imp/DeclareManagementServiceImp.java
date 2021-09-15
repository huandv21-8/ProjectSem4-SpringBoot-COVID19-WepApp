package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.DeclareRequest;
import com.example.footballshopwebapp.entity.Account;
import com.example.footballshopwebapp.entity.Commune;
import com.example.footballshopwebapp.entity.Question;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.AccountRepository;
import com.example.footballshopwebapp.repository.CommuneRepository;
import com.example.footballshopwebapp.repository.QuestionRepository;
import com.example.footballshopwebapp.service.DeclareManagementService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeclareManagementServiceImp implements DeclareManagementService {
    private final CommuneRepository communeRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;

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

            questionRepository.save(question);

            return new Message("Khai bao thanh cong");
        } catch (Exception e) {
            throw new SpringException("Loi roi");
        }


    }
}
