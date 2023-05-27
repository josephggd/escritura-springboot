package com.kobe2.escrituralocs.controllers;

import com.kobe2.escrituralocs.dtos.LocationRecord;
import com.kobe2.escrituralocs.entities.Location;
import com.kobe2.escrituralocs.exceptions.CannedStatementException;
import com.kobe2.escrituralocs.services.UnauthenticatedUserLocationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lu")
@RequiredArgsConstructor
public class UnauthenticatedUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());
    private final UnauthenticatedUserLocationService unauthenticatedUserLocationService;
    @GetMapping("/author/{author}")
    public List<LocationRecord> findByAuthor(@PathVariable String author) {
        try {
            List<Location> deviceNotes = unauthenticatedUserLocationService.findBySignature(author);
            return deviceNotes.stream()
                    .map(Location::toRecord)
                    .toList();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @GetMapping("/phrase")
    public List<LocationRecord> findByPhrase (@RequestParam String phrase) {
        try {
            List<Location> deviceNotes = unauthenticatedUserLocationService.findByBlurb(phrase);
            return deviceNotes.stream()
                    .map(Location::toRecord)
                    .toList();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
    @GetMapping("/loc/{lat},{lon}")
    public List<LocationRecord> findByLoc(
            @PathVariable float lat,
            @PathVariable float lon
    ) {
        try {
            List<Location> locations = unauthenticatedUserLocationService.findByLoc(lat, lon);
            return locations.stream()
                    .map(Location::toRecord)
                    .toList();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
}
