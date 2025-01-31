package com.meliuy.teteraJoa.teteraJoa.controller;

import com.meliuy.teteraJoa.teteraJoa.model.System;
import com.meliuy.teteraJoa.teteraJoa.service.SystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TeteraRestController {

    private final SystemService systemService;

    public TeteraRestController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/status")
    public System getStatus() {
        return systemService.getStatus();
    }

    @PostMapping("/change-default-system")
    public ResponseEntity<Void> changeDefaultSystem(@RequestParam String system) {
        systemService.changeSystem(system);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/teapot")
    public ResponseEntity<Void> postTeapot() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}