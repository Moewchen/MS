package com.bht.MediTrack.Vitaldatenmanagement.application;

import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.bht.MediTrack")
public class VitaldatenApplication {

	public static void main(String[] args) {
	LocalDateTime testDate = LocalDateTime.of(2024, 3, 15, 10, 30, 0);
	Vitaldaten vitaldaten = new Vitaldaten( UUID.randomUUID(), (short) 75, (byte) 16, (short) 120, (short) 80,37, testDate);
	System.out.println(vitaldaten);
	}
}


