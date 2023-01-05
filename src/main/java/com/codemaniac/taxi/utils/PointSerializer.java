package com.codemaniac.taxi.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class PointSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        String wkt = "POINT(" + point.getX() + " " + point.getY() + ")";
        jsonGenerator.writeString(wkt);
    }
}


