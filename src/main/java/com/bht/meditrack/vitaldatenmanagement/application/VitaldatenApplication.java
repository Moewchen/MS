package com.bht.meditrack.vitaldatenmanagement.application;

import com.bht.meditrack.vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.bht.meditrack")
public class VitaldatenApplication {

	/**
	 * Diese Klasse dient nur als Konfigurationsklasse für das Spring Boot Modul.
	 * Die Anwendung wird nicht als Standalone-Applikation ausgeführt, sondern
	 * als Teil der MediTrack-Gesamtanwendung gestartet.
	 */

	public static void main(String[] args) {
	LocalDateTime testDate = LocalDateTime.of(2024, 3, 15, 10, 30, 0);
	Vitaldaten vitaldaten = new Vitaldaten( UUID.randomUUID(), (short) 75, (byte) 16, (short) 120, (short) 80,37, testDate);
	System.out.println(vitaldaten);
	}
}


