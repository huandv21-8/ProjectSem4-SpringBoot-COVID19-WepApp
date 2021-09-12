package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.dto.request.SituationRequest;
import com.example.footballshopwebapp.dto.response.SituationResponse;
import com.example.footballshopwebapp.service.SituationService;
import com.example.footballshopwebapp.share.Message;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/situation")
@AllArgsConstructor
public class SituationController {
    private final SituationService situationService;

    @PostMapping("/createSituation")
    public Message createSituation(@RequestBody @Validated SituationRequest situationRequest) throws Exception {
        return situationService.createSituation(situationRequest);
    }

    @GetMapping("/listSituation")
    public List<SituationResponse> listSituation() {
        return situationService.listSituation();
    }

    @PutMapping("/editSituation/{idSituation}")
    public Message editSituation(@RequestBody @Validated SituationRequest situationRequest, @PathVariable(value = "idSituation") Long idSituation) throws Exception {
        return situationService.editSituation(situationRequest, idSituation);
    }

    @DeleteMapping("/deleteSituation/{idSituation}")
    public Message deleteSituation(@PathVariable(value = "idSituation") Long idSituation) throws Exception {
        return situationService.deleteSituation(idSituation);
    }
}
