package com.kobe2.escritura.services;

import com.kobe2.escritura.entities.Location;
import com.kobe2.escritura.repositories.LocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BasicService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    @NonNull
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
