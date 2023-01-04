package com.codemaniac.taxi.config;

import org.locationtech.jts.util.GeometricShapeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public GeometricShapeFactory geometricShapeFactory() {
        return new GeometricShapeFactory();
    }
}
