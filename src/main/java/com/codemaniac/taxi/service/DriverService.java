package com.codemaniac.taxi.service;

import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.entity.DriverStatus;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public void addDriver(Driver driver);
    public Driver getDriver(Long driverId);
    public List<Driver> getAllDrivers();
    public Driver updateDriver(Long driverId, Driver driver);
    public void updateDriverStatus(Long driverId, DriverStatus status);
    public void deleteDriver(Long driverId);
    public List<Driver> findDriversWithinRange(Double latitude, Double longitude, Integer radius);

}
