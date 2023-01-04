package com.codemaniac.taxi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private String email;
    private String license;
    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;
    @Column(columnDefinition = "Point")
    private Point location;
}
