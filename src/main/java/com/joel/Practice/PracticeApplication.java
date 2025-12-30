package com.joel.Practice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PracticeApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

	public static void main(String... args) {
       SpringApplication.run(PracticeApplication.class, args);
    }

}
