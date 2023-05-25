package com.kobe2.escritura.controllers;

import com.kobe2.escritura.dtos.LocationRecord;
import com.kobe2.escritura.entities.Location;
import com.kobe2.escritura.exceptions.CannedStatementException;
import com.kobe2.escritura.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/p")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/author/{author}")
    public List<LocationRecord> findByAuthor(@PathVariable String author) {
        try {
            List<Location> deviceNotes = userService.findBySignature(author);
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
            List<Location> deviceNotes = userService.findByBlurb(phrase);
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
            List<Location> locations = userService.findByLoc(lat, lon);
            return locations.stream()
                    .map(Location::toRecord)
                    .toList();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }

    @PostMapping("/new")
    public String saveNewNote (
            @RequestBody LocationRecord locationRecord
    ) {
        try {
            return userService.saveNewNote(locationRecord);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new CannedStatementException();
        }
    }
}
