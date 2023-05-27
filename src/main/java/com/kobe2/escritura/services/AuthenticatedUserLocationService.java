package com.kobe2.escritura.services;

import com.kobe2.escritura.dtos.LocationRecord;
import com.kobe2.escritura.entities.Location;
import com.kobe2.escritura.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserLocationService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    private final LocationRepository locationRepository;
    public String saveNewNote(LocationRecord lr) {
        logger.log(Level.FINEST, "saveNewNote");
        Location location = new Location(lr.lat(), lr.lon(), lr.blurb(), lr.signature());
        locationRepository.save(location);
        return "SUCCESS";
    }
}
