package com.codemaniac.taxi.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class GeoUtils {
    private static final GeometryFactory factory = new GeometryFactory();

    private  GeoUtils(){

    }

    public static Geometry wktToGeometry(String wellKnownText) throws ParseException {
        return new WKTReader().read(wellKnownText);
    }

    public static Point createPointUsingLatitudeLongitude(double latitude, double longitude) {

        return factory.createPoint(new Coordinate(latitude, longitude));
    }
}
