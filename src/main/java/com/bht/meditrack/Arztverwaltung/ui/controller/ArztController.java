package com.bht.meditrack.Arztverwaltung.ui.controller;

import com.bht.meditrack.Arztverwaltung.application.services.ArztService;
import com.bht.meditrack.Arztverwaltung.domain.model.Arzt;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aerzte")
public class ArztController {

    private final ArztService arztService;

    public ArztController(ArztService arztService) {
        this.arztService = arztService;
    }

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Arzt upsertArzt(@RequestParam final UUID arztId, @RequestBody final Arzt arzt) {
        return arztService.upsertArzt(arztId, arzt);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Arzt> upsertPatient(@RequestBody Arzt arzt) {
        UUID arztId = arzt.getId() != null ? arzt.getId() : UUID.randomUUID();
        Arzt upsertedArzt = arztService.upsertArzt(arztId, arzt);
        return ResponseEntity.status(HttpStatus.CREATED).body(upsertedArzt);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Arzt>> getAllAerzte() {
        List<Arzt> aerzte = arztService.getAllAerzte();
        return ResponseEntity.ok(aerzte);
    }
}
