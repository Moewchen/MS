package com.bht.MediTrack.Patientenverwaltung.ui.controller;

import com.bht.MediTrack.Patientenverwaltung.application.services.PatientService;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient upsertPatient(@RequestParam final UUID patientId, @RequestBody final Patient patient) {
        return patientService.upsertPatient(patientId, patient);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        UUID patientId = patient.getId() != null ? patient.getId() : UUID.randomUUID();
        Patient createdPatient = patientService.upsertPatient(patientId, patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        patients.forEach(patient -> patient.setVitaldaten(null));
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
    /*
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Autowired
    private InMemoryPatientRepository patientRepository;

    @Autowired
    private PublisherEvent eventPublisher;
//    @PostMapping
//    public Patient createPatient(@RequestBody PatientRequest request) {
//        return patientService.createPatient(request.getKrankenversicherungsnummer(), request.getKrankenkasse(), request.getAdresse(), request.getPersonendaten(), request.getKontaktdaten());
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createPatient(@RequestBody Patient request) {
        //TODO spaeter hoch zaehlen und nicht random gererieren lassen
        UUID patientId = UUID.randomUUID();
        Patient patient = new Patient(
                patientId,
                request.getKrankenkasse(),
                request.getKrankenversicherungsnummer(),
                request.getPersonendaten(),
                request.getKontaktdaten(),
                request.getAdresse()
        );
        Patient createdPatient = patientService.createPatient(patient);
        String responseMessage = "Patient mit den Daten " + createdPatient.getPersonendaten() + " wurde erstellt";
        return ResponseEntity.ok(responseMessage);
        /*return ResponseEntity.ok(createdPatient); */
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID id) {
        Optional<Patient> patient = patientService.findPatientById(id);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    */

