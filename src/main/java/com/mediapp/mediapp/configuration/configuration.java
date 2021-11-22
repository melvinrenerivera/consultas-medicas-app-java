package com.mediapp.mediapp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configuration {

    @Bean
    public ModelMapper nuevoMapper(){
        return new ModelMapper();
    }
}
