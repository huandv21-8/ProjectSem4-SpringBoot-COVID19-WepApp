package com.example.footballshopwebapp.controller.admin;

import com.example.footballshopwebapp.entity.Sick;
import com.example.footballshopwebapp.service.SickService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("v1/sick")
@AllArgsConstructor
public class SickController {
    private final SickService sickService;

    @GetMapping
    public ResponseEntity<List<Sick>> getAllPosts() {
        List<Sick> list= sickService.getAllSick();
        return status(HttpStatus.OK).body(list);
    }

    @PostMapping("/a")
    public String a() {
        return "huan dep trai";
    }
}
