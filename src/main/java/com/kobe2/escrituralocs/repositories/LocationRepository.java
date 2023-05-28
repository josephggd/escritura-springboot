package com.kobe2.escrituralocs.repositories;

import com.kobe2.escrituralocs.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Async
    @Query("SELECT L FROM Location L WHERE L.lat=:lat AND L.lon=:lon")
    CompletableFuture<List<Location>> asyncFindAllByLatLonClose(float lat, float lon);
    @Async
    @Query("SELECT L FROM Location L WHERE L.signature=:signature")
    CompletableFuture<List<Location>> asyncFindBySignature(String signature);
    @Async
    @Query("SELECT L FROM Location L WHERE L.blurb LIKE :word")
    CompletableFuture<List<Location>> asyncFindAllByBlurbContent(String word);

    @Query("SELECT L FROM Location L WHERE L.lat=:lat AND L.lon=:lon")
    List<Location> findAllByLatLonClose(float lat, float lon);
    @Query("SELECT L FROM Location L WHERE L.signature=:signature")
    List<Location> findBySignature(String signature);
    @Query("SELECT L FROM Location L WHERE L.blurb LIKE :word")
    List<Location> findAllByBlurbContent(String word);

}
