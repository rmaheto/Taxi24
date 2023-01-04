package com.codemaniac.taxi.web;

import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;


    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @RequestMapping
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return new ResponseEntity<>(driverService.getAllDrivers(),HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long driverId){
        return new ResponseEntity<>(driverService.getDriver(driverId),HttpStatus.OK);
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<Driver> updateDriverById(@PathVariable Long driverId,
                                                   @RequestBody Driver driver){
        return new ResponseEntity<>(driverService.updateDriver(driverId, driver), HttpStatus.OK);
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriverById(@PathVariable Long driverId){
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }
}
