package com.bht.MediTrack.shared.ui;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('clientuser')")
    public String hello() {
        return "hallo user funktioniert!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('clientadmin')")
    public String admin() {
        return "hallo admin funktioniert!";
    }
}