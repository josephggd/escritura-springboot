package com.kobe2.escrituralocs.entities;

import com.kobe2.escrituralocs.dtos.LocationRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location extends AbstractEntity{
    @NonNull
    private float lat;
    @NonNull
    private float lon;
    @NonNull
    private String blurb;
    @NonNull
    private String signature;

    public LocationRecord toRecord(){
        return new LocationRecord(this.lat, this.lon, this.blurb, this.signature);
    }
}
