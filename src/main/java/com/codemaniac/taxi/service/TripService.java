package com.codemaniac.taxi.service;

import com.codemaniac.taxi.dto.TripDto;
import com.codemaniac.taxi.entity.Trip;

import java.util.List;

public interface TripService {

    public TripDto requestTrip(Long riderId, Long driverId, TripDto tripDto);
    public Trip getTrip(Long tripId);
    public Trip updateTrip(Long tripId, Trip trip);
    public List<Trip> getAllTrips();
    public void deleteTripById(Long tripId);
}
