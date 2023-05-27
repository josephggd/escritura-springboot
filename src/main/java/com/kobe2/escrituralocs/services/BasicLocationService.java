package com.kobe2.escrituralocs.services;

import com.kobe2.escrituralocs.entities.Location;
import com.kobe2.escrituralocs.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BasicLocationService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    public final LocationRepository locationRepository;
    public Location findById(UUID id){
        logger.log(Level.FINEST, "findById");
        Optional<Location> optional = locationRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            logger.log(Level.WARNING, "NOT FOUND");
            throw new IllegalArgumentException("BAD ARG");
        }
    }
}
