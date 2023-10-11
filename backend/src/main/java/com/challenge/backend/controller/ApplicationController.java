package com.challenge.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.backend.dao.FarmRepository;
import com.challenge.backend.dao.MovementRepository;
import com.challenge.backend.model.Farm;
import com.challenge.backend.model.Movement;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private FarmRepository farmRepository;

    @GetMapping("/movements")
    public ResponseEntity<List<Movement>> getMovement() {
        List<Movement> movements = movementRepository.findAll();
        return ResponseEntity.ok().headers(getHeaders()).body(movements);
    }

    @GetMapping("/farms")
    public ResponseEntity<List<Farm>> getFarms() {
        return ResponseEntity.ok().headers(getHeaders()).body(farmRepository.findAll());
    }

    @GetMapping("/farm/{premiseId}")
    public ResponseEntity<Farm> getFarm(@PathVariable String premiseId) {
        Optional<Farm> farm = farmRepository.findById(premiseId);
        if (!farm.isPresent()) {
            return ResponseEntity.ok().headers(getHeaders()).body(null);
        }
        return ResponseEntity.ok().headers(getHeaders()).body(farm.get());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "*");
        return headers;
    }
}
