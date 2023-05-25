package com.kobe2.escritura.services;

import com.kobe2.escritura.dtos.LocationRecord;
import com.kobe2.escritura.entities.Location;
import com.kobe2.escritura.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
    private final LocationRepository locationRepository;
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    public UserService(
            LocationRepository locationRepository
    ) {
        this.locationRepository = locationRepository;
    }
    public List<Location> findBySignature(String deviceId){
        logger.log(Level.FINEST, "findBySignature");
        return locationRepository.findBySignature(deviceId);
    }
    public List<Location> findByBlurb(String word){
        logger.log(Level.FINEST, "findByBlurb");
        return locationRepository.findAllByBlurbContent(word);
    }
    public List<Location> findByLoc(float lat, float lon) {
        logger.log(Level.FINEST, "findByLoc");
        return locationRepository.findAllByLatLonClose(lat, lon);
    }
    public String saveNewNote(LocationRecord lr) {
        logger.log(Level.FINEST, "saveNewNote");
        Location location = new Location(lr.lat(), lr.lon(), lr.blurb(), lr.signature());
        locationRepository.save(location);
        return "SUCCESS";
    }
}
