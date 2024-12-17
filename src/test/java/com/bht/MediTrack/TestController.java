package com.bht.MediTrack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Dieser Endpunkt ist öffentlich zugänglich.";
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "Dieser Endpunkt ist geschützt und erfordert eine gültige Authentifizierung.";
    }
}