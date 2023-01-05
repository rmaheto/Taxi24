package com.codemaniac.taxi.repository;

import com.codemaniac.taxi.entity.Driver;
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

    @Query("SELECT d FROM Driver d WHERE ST_DWithin(location, ST_GeomFromText(:filter), 3000)")
    List<Driver> findClosestDriversWithin(@Param("filter") Geometry filter);



}
