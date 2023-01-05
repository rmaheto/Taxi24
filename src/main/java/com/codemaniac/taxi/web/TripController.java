package com.codemaniac.taxi.web;

import com.codemaniac.taxi.dto.TripDto;
import com.codemaniac.taxi.entity.Trip;
import com.codemaniac.taxi.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/{riderId}/{driverId}")
    public ResponseEntity<TripDto> requestTrip(@PathVariable Long riderId, @PathVariable Long driverId,
                                               @RequestBody TripDto tripDto) {
        return new ResponseEntity<TripDto>(tripService.requestTrip(riderId, driverId, tripDto), HttpStatus.CREATED);
    }

    @PostMapping("/complete/{driverId}/{tripId}")
    public ResponseEntity<String> completeTrip(@PathVariable Long tripId,
                                               @PathVariable Long driverId) {
        tripService.completeTrip(tripId,driverId);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Trip>> getAllActiveTrips(){
        return new ResponseEntity<>(tripService.getAllActiveTrips(),HttpStatus.OK);
    }
}
