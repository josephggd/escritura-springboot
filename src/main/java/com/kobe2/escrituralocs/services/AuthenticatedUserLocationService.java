package com.kobe2.escrituralocs.services;

import com.kobe2.escrituralocs.dtos.LocationRecord;
import com.kobe2.escrituralocs.entities.Location;
import com.kobe2.escrituralocs.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserLocationService {
    private final Logger logger = Logger.getLogger(this.getClass().toString());
    private final LocationRepository locationRepository;
    public void saveNewNote(LocationRecord lr) {
        logger.log(Level.FINEST, "saveNewNote");
        Location location = new Location(lr.lat(), lr.lon(), lr.blurb(), lr.signature());
        locationRepository.save(location);
    }
    CompletableFuture<List<Location>> asyncFindBySignature(String deviceId) {
        logger.log(Level.FINEST, "findFutureBySignature");
        return locationRepository.asyncFindBySignature(deviceId);
    }
    public Flux<LocationRecord> findBySignature(String deviceId){
        logger.log(Level.FINEST, "findBySignature");
        CompletableFuture<List<Location>> locations = asyncFindBySignature(deviceId);
        return futureToFlux(locations);
    }
    private CompletableFuture<List<Location>> asyncFindByBlurb(String word){
        logger.log(Level.FINEST, "findByBlurb");
        return locationRepository.asyncFindAllByBlurbContent(word);
    }
    public Flux<LocationRecord> findByBlurb(String word){
    logger.log(Level.FINEST, "findByBlurb");
    CompletableFuture<List<Location>> locations = asyncFindByBlurb(word);
    return futureToFlux(locations);
    }
    private CompletableFuture<List<Location>> asyncFindByLoc(float lat, float lon) {
        logger.log(Level.FINEST, "findFutureByLoc");
        return locationRepository.asyncFindAllByLatLonClose(lat, lon);
    }
    public Flux<LocationRecord> findByLoc(float lat, float lon) {
        logger.log(Level.FINEST, "findByLoc");
        CompletableFuture<List<Location>> locations = asyncFindByLoc(lat, lon);
        return futureToFlux(locations);
    }
    public Flux<LocationRecord> futureToFlux(CompletableFuture<List<Location>> locations) {
        logger.log(Level.FINEST, "futureToFlux");
        return Flux.create(emitter -> locations.whenComplete((locationList, exception) -> {
            if (exception == null) {
                locationList.stream().map(Location::toRecord).toList().forEach(emitter::next);
                emitter.complete();
            } else {
                emitter.complete();
            }
        }));
    }
}
