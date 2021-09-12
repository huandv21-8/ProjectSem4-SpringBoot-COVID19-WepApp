package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.dto.response.SituationResponse;
import com.example.footballshopwebapp.share.Message;

import java.util.List;

public interface SituationService {

    Message createSituation(SituationRequest situationRequest) throws Exception;

    List<SituationResponse> listSituation();

    Message editSituation(SituationRequest situationRequest, Long idSituation) throws Exception;

    Message deleteSituation(Long idSituation) throws Exception;
}
