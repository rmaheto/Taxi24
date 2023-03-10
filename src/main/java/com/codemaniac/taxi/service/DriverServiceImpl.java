package com.codemaniac.taxi.service;

import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.entity.DriverStatus;
import com.codemaniac.taxi.exception.EntityNotFoundException;
import com.codemaniac.taxi.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.GeometricShapeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private GeometricShapeFactory geometricShapeFactory;

    @Autowired
    private EmailService emailService;

    @Value("${msgs.registrations.subject}")
    private String subject;

    @Value("${msgs.registrations.welcome}")
    private String welcomeMessage;

    @Override
    public void addDriver(Driver driver) {
        driverRepository.save(driver);
        String msg = String.format(welcomeMessage,driver.getName());
        emailService.sendSimpleMessage(driver.getEmail(), subject,msg);
    }

    @Override
    public Driver getDriver(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(driverId));
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver updateDriver(Long driverId, Driver driver) {
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(driverId));

        existingDriver.setName(driver.getName());
        existingDriver.setGender(driver.getGender());
        existingDriver.setDriverStatus(driver.getDriverStatus());
        existingDriver.setLicense(driver.getLicense());
        existingDriver.setPhoneNumber(driver.getPhoneNumber());
        driverRepository.save(existingDriver);
        return existingDriver;
    }

    @Override
    public void updateDriverStatus(Long driverId, DriverStatus status) {
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(driverId));
        existingDriver.setDriverStatus(status);
    }

    @Override
    public List<Driver> findDriversWithinRange(Double latitude, Double longitude, Integer radius) {
        log.info("Searching restaurants {} km to point {} lat., {} long.", radius, latitude, longitude);
        Geometry circle = this.createCircle(latitude, longitude, radius);
        List<Driver> drivers = this.driverRepository.findDriversWithin(circle);
        return drivers;
    }

    private Geometry createCircle(Double latitude, Double longitude, Integer radius) {
        geometricShapeFactory.setNumPoints(32);
        geometricShapeFactory.setCentre(new Coordinate(latitude, longitude));
        geometricShapeFactory.setSize(radius * 2);
        return geometricShapeFactory.createCircle();
    }

    @Override
    public void deleteDriver(Long driverId) {
        driverRepository.deleteById(driverId);
    }
}
