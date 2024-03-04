package com.maneira.mongoproject.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DoubleReadConverter());
        converters.add(new DoubleWriteConverter());
        return new MongoCustomConversions(converters);
    }

    private static class DoubleReadConverter implements Converter<Double, Double> {
        @Override
        public Double convert(Double source) {
            return source;
        }
    }

    private static class DoubleWriteConverter implements Converter<Double, Double> {
        @Override
        public Double convert(Double source) {
            return source;
        }
    }
}

