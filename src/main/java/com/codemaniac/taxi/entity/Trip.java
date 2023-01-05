package com.codemaniac.taxi.entity;

import com.codemaniac.taxi.utils.PointDeserializer;
import com.codemaniac.taxi.utils.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @Column(columnDefinition = "Point")
    @JsonSerialize(using = PointSerializer.class)
    @JsonDeserialize(using = PointDeserializer.class)
    private Point startLocation;
    @Column(columnDefinition = "Point")
    @JsonSerialize(using = PointSerializer.class)
    @JsonDeserialize(using = PointDeserializer.class)
    private Point endLocation;
    private TripStatus status;
}
