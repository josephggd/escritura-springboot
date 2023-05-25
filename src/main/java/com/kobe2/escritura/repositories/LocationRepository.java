package com.kobe2.escritura.repositories;

import com.kobe2.escritura.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("SELECT L FROM Location L WHERE L.lat=:lat AND L.lon=:lon")
    List<Location> findAllByLatLonClose(float lat, float lon);
    @Query("SELECT L FROM Location L WHERE L.signature=:signature")
    List<Location> findBySignature(String signature);
    @Query("SELECT L FROM Location L WHERE L.blurb LIKE :word")
    List<Location> findAllByBlurbContent(String word);
}
