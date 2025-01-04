package com.bht.meditrack.shared.ui;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('user_patient', 'user_arzt')")
    public String hello() {
        return "hallo user funktioniert!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "hallo admin funktioniert!";
    }
}