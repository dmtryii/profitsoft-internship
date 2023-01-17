package com.dmtryii.task.service;

import com.dmtryii.task.dto.director.DirectorDetailsDto;

import java.util.List;

public interface DirectorService {
    List<DirectorDetailsDto> findAll();
}
