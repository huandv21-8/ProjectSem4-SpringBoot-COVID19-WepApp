package com.example.footballshopwebapp.service.imp;

import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.entity.Situation;
import com.example.footballshopwebapp.repository.SituationRepository;
import com.example.footballshopwebapp.repository.UserRepository;
import com.example.footballshopwebapp.service.SituationService;
import com.example.footballshopwebapp.share.Message;
import com.example.footballshopwebapp.share.helper.DateHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    private Situation situationRequestMapSituation(SituationRequest situationRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Situation situation = new Situation();
        situation.setContent(situationRequest.getContent());
        situation.setCreatedAt(dateHelper.getDateNow());
        situation.setUpdatedAt(dateHelper.getDateNow());
        situation.setUser(userRepository.findByEmail(((UserDetails) principal).getUsername()).get());
        return situation;
    }
}
