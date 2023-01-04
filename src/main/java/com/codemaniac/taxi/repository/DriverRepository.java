package com.codemaniac.taxi.repository;

import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.entity.Trip;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE within(d.location, :filter) = true")
    List<Driver> findDriversWithin(@Param("filter") Geometry filter);
}
