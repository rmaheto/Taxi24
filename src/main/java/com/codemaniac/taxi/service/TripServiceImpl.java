package com.codemaniac.taxi.service;

import com.codemaniac.taxi.dto.TripDto;
import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.entity.Rider;
import com.codemaniac.taxi.entity.Trip;
import com.codemaniac.taxi.exception.EntityNotFoundException;
import com.codemaniac.taxi.repository.DriverRepository;
import com.codemaniac.taxi.repository.RiderRepository;
import com.codemaniac.taxi.repository.TripRepository;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private RiderRepository riderRepository;
    private DriverRepository driverRepository;
    private final static GeometryFactory factory = new GeometryFactory();
    @Autowired
    private ModelMapper modelMapper;

    public TripServiceImpl(TripRepository tripRepository, RiderRepository riderRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.riderRepository = riderRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public TripDto requestTrip(Long riderId,Long driverId, TripDto tripDto) {
        Point startPoint=null;
        Point endPoint=null;
        Rider existingRider = riderRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException(riderId));

        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(driverId));
        Trip trip = new Trip();
        try {
            startPoint = factory.createPoint(new Coordinate(tripDto.getStartLatitude(), tripDto.getStartLongitude()));
            endPoint = factory.createPoint(new Coordinate(tripDto.getEndLatitude(), tripDto.getEndLongitude()));
            trip.setDriver(existingDriver);
            trip.setRider(existingRider);
            trip.setStart(startPoint);
            trip.setEnd(endPoint);
            trip.setStatus(tripDto.getStatus());
            tripRepository.save(trip);
        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new RuntimeException(e);
        }

        return modelMapper.map(trip, TripDto.class);
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
      return null;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public void deleteTripById(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
