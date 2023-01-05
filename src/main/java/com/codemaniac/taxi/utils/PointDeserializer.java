package com.codemaniac.taxi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


import lombok.SneakyThrows;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;

import java.io.IOException;

public class PointDeserializer extends JsonDeserializer<Point> {

    @SneakyThrows
    @Override
    public Point deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String wkt = jsonParser.getValueAsString();
        WKTReader wktReader = new WKTReader();
        Point point = (Point) wktReader.read("POINT(1 1)");
        double latitude = point.getY();
        double longitude = point.getX();
        return GeoUtils.createPointUsingLatitudeLongitude(latitude,longitude);
    }
}