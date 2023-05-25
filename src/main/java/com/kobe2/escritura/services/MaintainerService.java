package com.kobe2.escritura.services;

import com.kobe2.escritura.entities.Location;
import com.kobe2.escritura.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MaintainerService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    private final UserService userService;
    private final BasicService basicService;
    private final LocationRepository locationRepository;
    public MaintainerService (
            UserService userService,
            BasicService basicService,
            LocationRepository locationRepository
    ) {
        this.userService = userService;
        this.basicService = basicService;
        this.locationRepository = locationRepository;
    }
    public void deleteById(UUID id){
        logger.log(Level.FINEST, "deleteById");
        Location location = basicService.findById(id);
        locationRepository.delete(location);
    }
    public void setVisibilityById(UUID id, boolean visibility){
        logger.log(Level.FINEST, "setVisibilityById");
        Location location = basicService.findById(id);
        location.setVisible(visibility);
        locationRepository.save(location);
    }
    public void deleteByWord(String phrase){
        logger.log(Level.FINEST, "deleteByWord");
        List<Location> locations = userService.findByBlurb(phrase);
        locationRepository.deleteAll(locations);
    }
    public void setVisibilityByWord(String phrase, boolean visibility){
        logger.log(Level.FINEST, "setVisibilityByWord");
        List<Location> unmodifiedLocations = userService.findByBlurb(phrase);
        setVisibilityForList(unmodifiedLocations, visibility);
    }
    public void deleteByLoc(float lat, float lon){
        logger.log(Level.FINEST, "deleteByLoc");
        List<Location> locations = userService.findByLoc(lat, lon);
        locationRepository.deleteAll(locations);
    }
    public void setVisibilityByLoc(float lat, float lon, boolean visibility){
        logger.log(Level.FINEST, "setVisibilityByLoc");
        List<Location> unmodifiedLocations = userService.findByLoc(lat, lon);
        setVisibilityForList(unmodifiedLocations, visibility);
    }
    public void deleteByAuthor(String author){
        logger.log(Level.FINEST, "deleteByAuthor");
        List<Location> locations = userService.findBySignature(author);
        locationRepository.deleteAll(locations);
    }
    public void setVisibilityByAuthor(String author, boolean visibility){
        logger.log(Level.FINEST, "setVisibilityByWord");
        List<Location> unmodifiedLocations = userService.findBySignature(author);
        setVisibilityForList(unmodifiedLocations, visibility);
    }
    public void setVisibilityForList(List<Location> unmodifiedLocations, boolean visibility){
        logger.log(Level.FINEST, "setVisibilityForList");
        List<Location> modifiedLocations = new ArrayList<>();
        for (Location location : unmodifiedLocations) {
            location.setVisible(visibility);
            modifiedLocations.add(location);
        }
        locationRepository.saveAll(modifiedLocations);
    }


}
