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
        Point point = (Point) wktReader.read(wkt);
        double latitude = point.getX();
        double longitude = point.getY();
        return GeoUtils.createPointUsingLatitudeLongitude(latitude,longitude);
    }
}