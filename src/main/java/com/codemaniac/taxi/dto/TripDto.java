package com.codemaniac.taxi.dto;

import com.codemaniac.taxi.entity.TripStatus;
import lombok.Data;

@Data
public class TripDto {

    private Long tripId;
    private Long riderId;
    private Long driverId;
    private Double startLatitude;
    private Double startLongitude;
    private Double endLatitude;
    private Double endLongitude;
    private TripStatus status;
}
