package com.kobe2.escrituralocs.controllers;

import com.kobe2.escrituralocs.entities.Location;
import com.kobe2.escrituralocs.exceptions.CannedStatementException;
import com.kobe2.escrituralocs.services.BasicLocationService;
import com.kobe2.escrituralocs.services.MaintainerLocationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MaintainerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());
    private final MaintainerLocationService maintainerLocationService;
    private final BasicLocationService basicLocationService;
    @GetMapping("/id/{stringId}")
    public Location findById(@PathVariable String stringId) {
        try {
            UUID id = UUID.fromString(stringId);
            return basicLocationService.findById(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/id/{stringId}")
    public String deleteById(@PathVariable String stringId) {
        try {
            UUID id = UUID.fromString(stringId);
            maintainerLocationService.deleteById(id);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @PutMapping("/id/{stringId}")
    public String setInvisibleById(@PathVariable String stringId) {
        try {
            UUID id = UUID.fromString(stringId);
            maintainerLocationService.setVisibilityById(id, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/author/{author}")
    public String deleteByAuthor(@PathVariable String author) {
        try {
            maintainerLocationService.deleteByAuthor(author);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/phrase")
    public String deleteByPhrase (@RequestParam String phrase) {
        try {
            maintainerLocationService.deleteByWord(phrase);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/loc/{lat},{lon}")
    public String deleteByLoc(
            @PathVariable float lat,
            @PathVariable float lon
    ) {
        try {
            maintainerLocationService.deleteByLoc(lat, lon);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @PutMapping("/author/{author}")
    public String setInvisibleByAuthor(@PathVariable String author) {
        try {
            maintainerLocationService.setVisibilityByAuthor(author, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @PutMapping("/phrase")
    public String setInvisibleByPhrase (@RequestParam String phrase) {
        try {
            maintainerLocationService.setVisibilityByWord(phrase, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @PutMapping("/loc/{lat},{lon}")
    public String setInvisibleByLoc(
            @PathVariable float lat,
            @PathVariable float lon
    ) {
        try {
            maintainerLocationService.setVisibilityByLoc(lat, lon, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
}
