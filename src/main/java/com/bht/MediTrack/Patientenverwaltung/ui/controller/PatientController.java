package com.bht.MediTrack.Patientenverwaltung.ui.controller;

import com.bht.MediTrack.Patientenverwaltung.application.services.PatientService;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

//    @PostMapping
//    public Patient createPatient(@RequestBody PatientRequest request) {
//        return patientService.createPatient(request.getKrankenversicherungsnummer(), request.getKrankenkasse(), request.getAdresse(), request.getPersonendaten(), request.getKontaktdaten());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID id) {
        Optional<Patient> patient = patientService.findPatientById(id);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    public static class PatientRequest {
        private Krankenkasse krankenkasse;
        private String krankenversicherungsnummer;
        private Personendaten personendaten;
        private Kontaktdaten kontaktdaten;
        private Adresse adresse;

        public Krankenkasse getKrankenkasse() {
            return krankenkasse;
        }

        public String getKrankenversicherungsnummer() {
            return krankenversicherungsnummer;
        }

        public Personendaten getPersonendaten() {
            return personendaten;
        }

        public Kontaktdaten getKontaktdaten() {
            return kontaktdaten;
        }

        public Adresse getAdresse() {
            return adresse;
        }
    }
}
