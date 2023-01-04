package com.codemaniac.taxi.web;

import com.codemaniac.taxi.entity.Rider;
import com.codemaniac.taxi.service.RiderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riders")
public class RiderController {

    private RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }


    @PostMapping
    public ResponseEntity<Rider> addRider(@RequestBody Rider rider) {
        return new ResponseEntity<>(riderService.addRider(rider), HttpStatus.CREATED);
    }

    @RequestMapping
    public ResponseEntity<List<Rider>> getAllRiders(){
        return new ResponseEntity<>(riderService.getAllRiders(),HttpStatus.OK);
    }

    @GetMapping("/{riderId}")
    public ResponseEntity<Rider> getRiderById(@PathVariable Long riderId){
        return new ResponseEntity<>(riderService.getRider(riderId), HttpStatus.OK);
    }

    @PutMapping("/{riderId}")
    public ResponseEntity<Rider> updateRiderById(@PathVariable Long riderId,
                                                   @RequestBody Rider rider){
        return new ResponseEntity<>(riderService.updateRider(riderId, rider), HttpStatus.OK);
    }

    @DeleteMapping("/{riderId}")
    public ResponseEntity<Void> deleteDriverById(@PathVariable Long riderId){
        riderService.deleteRider(riderId);
        return ResponseEntity.noContent().build();
    }
}
