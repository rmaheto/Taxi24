package com.codemaniac.taxi.service;

import com.codemaniac.taxi.entity.*;
import com.codemaniac.taxi.exception.EntityNotFoundException;
import com.codemaniac.taxi.repository.DriverRepository;
import com.codemaniac.taxi.repository.RiderRepository;
import com.codemaniac.taxi.repository.TripRepository;
import org.locationtech.jts.geom.GeometryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private RiderRepository riderRepository;
    private DriverRepository driverRepository;
    private DriverService driverService;

    private static final GeometryFactory factory = new GeometryFactory();
    @Autowired
    private ModelMapper modelMapper;

    public TripServiceImpl(TripRepository tripRepository, RiderRepository riderRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.riderRepository = riderRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Trip requestTrip(Long riderId, Long driverId, Trip trip) {
        Rider existingRider = riderRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException(riderId));

        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(driverId));

            trip.setDriver(existingDriver);
            trip.setRider(existingRider);
            tripRepository.save(trip);

        return trip;
    }

    @Override
    public void completeTrip(Long driverId, Long tripId) {
        Trip foundTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException(tripId));
        foundTrip.setStatus(TripStatus.COMPLETED);
        driverService.updateDriverStatus(driverId, DriverStatus.AVAILABLE);
    }

    @Override
    public void cancelTrip(Long tripId) {
        Trip foundTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException(tripId));
        foundTrip.setStatus(TripStatus.COMPLETED);
        foundTrip.setStatus(TripStatus.CANCELLED);
        tripRepository.save(foundTrip);
    }

    @Override
    public Trip getTrip(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException(tripId));
    }

    @Override
    public Trip updateTrip(Long tripId, Trip trip) {
        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException(tripId));

        existingTrip.setStatus(trip.getStatus());
        return null;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> getAllActiveTrips() {
        return tripRepository.findAllByStatusEquals(TripStatus.STARTED);
    }

    @Override
    public void deleteTripById(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
