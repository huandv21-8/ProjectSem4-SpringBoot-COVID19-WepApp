package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.service.SituationService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/situation")
@AllArgsConstructor
public class SituationController {
    private final SituationService situationService;

    @PostMapping("/createSituation")
    public Message createSituation(@RequestBody @Validated SituationRequest situationRequest) throws Exception {
        return situationService.createSituation(situationRequest);
    }
}
