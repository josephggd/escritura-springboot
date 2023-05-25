package com.kobe2.escritura.controllers;

import com.kobe2.escritura.entities.Location;
import com.kobe2.escritura.exceptions.CannedStatementException;
import com.kobe2.escritura.services.BasicService;
import com.kobe2.escritura.services.MaintainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/m")
public class MaintainerController {
    private final MaintainerService maintainerService;
    private final BasicService basicService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());

    public MaintainerController(
            MaintainerService maintainerService,
            BasicService basicService) {
        this.maintainerService = maintainerService;
        this.basicService = basicService;
    }

    @GetMapping("/id/{stringId}")
    public Location findById(@PathVariable String stringId) {
        try {
            UUID id = UUID.fromString(stringId);
            return basicService.findById(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/id/{stringId}")
    public String deleteById(@PathVariable String stringId) {
        try {
            UUID id = UUID.fromString(stringId);
            maintainerService.deleteById(id);
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
            maintainerService.setVisibilityById(id, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/author/{author}")
    public String deleteByAuthor(@PathVariable String author) {
        try {
            maintainerService.deleteByAuthor(author);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @DeleteMapping("/phrase")
    public String deleteByPhrase (@RequestParam String phrase) {
        try {
            maintainerService.deleteByWord(phrase);
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
            maintainerService.deleteByLoc(lat, lon);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @PutMapping("/author/{author}")
    public String setInvisibleByAuthor(@PathVariable String author) {
        try {
            maintainerService.setVisibilityByAuthor(author, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @PutMapping("/phrase")
    public String setInvisibleByPhrase (@RequestParam String phrase) {
        try {
            maintainerService.setVisibilityByWord(phrase, false);
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
            maintainerService.setVisibilityByLoc(lat, lon, false);
            return "OK";
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
}
