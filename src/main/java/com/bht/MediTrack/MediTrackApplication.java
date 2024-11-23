package com.bht.MediTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bht.MediTrack.Vitaldatenmanagement.ui.controller.VitaldatenController;
import java.time.LocalDate;

@SpringBootApplication
public class MediTrackApplication {

    public static void main(String[] args) {

        SpringApplication.run(MediTrackApplication.class, args);

    }
}

