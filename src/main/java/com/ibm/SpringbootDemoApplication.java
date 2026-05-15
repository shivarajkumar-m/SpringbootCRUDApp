package com.ibm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootDemoApplication
        extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(
                SpringbootDemoApplication.class,
                args
        );
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {

        return application.sources(
                SpringbootDemoApplication.class
        );
    }

    @Bean
    public ModelMapper getMapper() {

        return new ModelMapper();
    }
}
