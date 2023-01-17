package com.dmtryii.task.controller;

import com.dmtryii.task.dto.director.DirectorDetailsDto;
import com.dmtryii.task.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<DirectorDetailsDto>> getAllDirector() {
        List<DirectorDetailsDto> director = directorService.findAll();
        if (director.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(director);
        }
    }

}
