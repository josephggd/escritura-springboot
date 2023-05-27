package com.kobe2.escrituralocs.services;

import com.kobe2.escrituralocs.entities.Location;
import com.kobe2.escrituralocs.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UnauthenticatedUserLocationService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    private final LocationRepository locationRepository;
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
}
