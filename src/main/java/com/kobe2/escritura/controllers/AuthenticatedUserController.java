package com.kobe2.escritura.controllers;

import com.kobe2.escritura.dtos.LocationRecord;
import com.kobe2.escritura.exceptions.CannedStatementException;
import com.kobe2.escritura.services.AuthenticatedUserLocationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/la")
@RequiredArgsConstructor
public class AuthenticatedUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());
    private final AuthenticatedUserLocationService authenticatedUserLocationService;

    @PostMapping("/new")
    public String saveNewNote (
            @RequestBody LocationRecord locationRecord
    ) {
        try {
            return authenticatedUserLocationService.saveNewNote(locationRecord);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
}
