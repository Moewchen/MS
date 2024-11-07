package com.bht.MediTrack;

import com.bht.MediTrack.Entities.Nutzer;
import com.bht.MediTrack.Repositories.InMemoryNutzerRepository;
import com.bht.MediTrack.Services.NutzerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;

@SpringBootApplication
public class MediTrackApplication {

	public static void main(String[] args) {

		SpringApplication.run(MediTrackApplication.class, args);

		// Repository und Service initialisieren
		InMemoryNutzerRepository nutzerRepository = new InMemoryNutzerRepository();
		NutzerService nutzerService = new NutzerService(nutzerRepository);

		// Beispiel-Nutzer erstellen
		Nutzer nutzer1 = nutzerService.createNutzer("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max.mustermann@example.com", "Musterstraße 1");
		Nutzer nutzer2 = nutzerService.createNutzer("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10), "+4987654321", "anna.anders@example.com", "Hauptstraße 2");

		// Alle Nutzer anzeigen
		System.out.println("Alle Nutzer:");
		nutzerService.getAllNutzer().forEach(System.out::println);

		// Nutzer nach E-Mail finden
		System.out.println("\nNutzer mit E-Mail 'max.mustermann@example.com':");
		nutzerService.findNutzerByEmail("max.mustermann@example.com").ifPresent(System.out::println);

		// Nutzer nach Namen filtern
		System.out.println("\nNutzer mit dem Namen 'Anna':");
		nutzerService.findNutzerByName("Anna").forEach(System.out::println);

		// Nutzer vor einem bestimmten Jahr geboren
		System.out.println("\nNutzer, die vor 1990 geboren sind:");
		nutzerService.findNutzerBornBefore(1990).forEach(System.out::println);

		// Überprüfung, ob eine E-Mail existiert
		System.out.println("\nExistiert E-Mail 'anna.anders@example.com'? " + nutzerService.emailExists("anna.anders@example.com"));

		// Löschen eines Nutzers
		nutzerService.deleteNutzer(nutzer1.getId());
		System.out.println("\nAlle Nutzer nach dem Löschen von Nutzer1:");
		nutzerService.getAllNutzer().forEach(System.out::println);


	}
}


