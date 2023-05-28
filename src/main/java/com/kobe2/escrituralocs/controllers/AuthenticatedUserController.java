package com.kobe2.escrituralocs.controllers;

import com.kobe2.escrituralocs.dtos.LocationRecord;
import com.kobe2.escrituralocs.exceptions.CannedStatementException;
import com.kobe2.escrituralocs.services.AuthenticatedUserLocationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AuthenticatedUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());
    private final AuthenticatedUserLocationService authenticatedUserLocationService;
    @PostMapping("/locs")
    public void saveNewLoc(@RequestBody LocationRecord lr) {
        try {
            authenticatedUserLocationService.saveNewNote(lr);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @GetMapping("/author/{author}")
    public Flux<LocationRecord> findByAuthor(@PathVariable String author) {
        try {
            return authenticatedUserLocationService.findBySignature(author);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @GetMapping("/phrase")
    public Flux<LocationRecord> findByPhrase (@RequestParam String phrase) {
        try {
            return authenticatedUserLocationService.findByBlurb(phrase);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @GetMapping("/loc/{lat},{lon}")
    public Flux<LocationRecord> findByLoc(
            @PathVariable float lat,
            @PathVariable float lon
    ) {
        try {
            return authenticatedUserLocationService.findByLoc(lat, lon);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
}
