package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.dto.response.SituationResponse;
import com.example.footballshopwebapp.entity.Situation;
import com.example.footballshopwebapp.exceptions.SpringException;
import com.example.footballshopwebapp.repository.SituationRepository;
import com.example.footballshopwebapp.repository.UserRepository;
import com.example.footballshopwebapp.service.SituationService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.DateHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SituationServiceImp implements SituationService {

    private final SituationRepository situationRepository;
    private final UserRepository userRepository;
    private final DateHelper dateHelper;

    @Override
    public Message createSituation(SituationRequest situationRequest) throws Exception {
        situationRequestMapSituation(situationRequest);
        situationRepository.save(situationRequestMapSituation(situationRequest));
        return new Message("success");
    }

    @Override
    public List<SituationResponse> listSituation() {
        return situationRepository.findAll().stream()
                .map(this::situationMapSituationResponse).collect(Collectors.toList());
    }

    @Override
    public Message editSituation(SituationRequest situationRequest, Long idSituation) throws Exception{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Situation situation = situationRepository.findById(idSituation).orElseThrow(()->new SpringException("Situation not found - "+ idSituation));
        situation.setContent(situationRequest.getContent());
        situation.setUpdatedAt(dateHelper.getDateNow());
        situation.setUser(userRepository.findByEmail(((UserDetails) principal).getUsername()).get());
        situationRepository.save(situation);
        return new Message("success");
    }

    @Override
    public Message deleteSituation(Long idSituation) throws Exception {
        Situation situation = situationRepository.findById(idSituation).orElseThrow(()->new SpringException("Situation not found - "+ idSituation));
        situation.setActive(false);
        situationRepository.save(situation);
        return new Message("success");
    }


    private SituationResponse situationMapSituationResponse(Situation situation) {
        SituationResponse situationResponse = new SituationResponse();
        situationResponse.setContent(situation.getContent());
        situationResponse.setUpdatedAt(situation.getUpdatedAt());
        return situationResponse;
    }

    private Situation situationRequestMapSituation(SituationRequest situationRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Situation situation = new Situation();
        situation.setContent(situationRequest.getContent());
        situation.setCreatedAt(dateHelper.getDateNow());
        situation.setUpdatedAt(dateHelper.getDateNow());
        situation.setUser(userRepository.findByEmail(((UserDetails) principal).getUsername()).get());
        situation.setActive(true);
        return situation;
    }
}
