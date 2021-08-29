package com.example.footballshopwebapp.service;

import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.share.Message;

public interface SituationService {

    Message createSituation(SituationRequest situationRequest) throws Exception;
}
