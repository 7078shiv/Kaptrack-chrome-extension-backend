package com.example.demo.controller;

import com.example.demo.dto.kapTrackDto;
import com.example.demo.service.KapTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/v1/kapTrack")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class kapTrackController {
    private final KapTrackService kapTrackService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveData(@RequestBody kapTrackDto dto){
        return kapTrackService.saveData(dto);
    }
}
