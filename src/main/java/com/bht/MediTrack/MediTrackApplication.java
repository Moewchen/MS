package com.bht.MediTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class MediTrackApplication {

    public static void main(String[] args) {

        SpringApplication.run(MediTrackApplication.class, args);

    }


}

