package com.codemaniac.taxi.repository;

import com.codemaniac.taxi.entity.Driver;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE ST_Distance(d.location, :point) >= :distance")
    List<Driver> findByDriverLocationWithinDistance(@Param("point") Point point,@Param("distance") double distance);



}
