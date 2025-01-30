package com.meliuy.teteraJoa.teteraJoa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TeteraRestController {

    private static final Map<String, String> SYSTEM_CODES = new HashMap<>() {{
        put("navigation", "NAV-01");
        put("communications", "COM-02");
        put("life_support", "LIFE-03");
        put("engines", "ENG-04");
        put("deflector_shield", "SHLD-05");
    }};
    private String damagedSystem = "navigation";

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("damaged_system", damagedSystem);
        return response;
    }

    @PostMapping("/change-default-system")
    public ResponseEntity<Void> changeDefaultSystem(@RequestParam String system) {
        if (SYSTEM_CODES.containsKey(system)) {
            this.damagedSystem = system;
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/teapot")
    public ResponseEntity<Void> postTeapot() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}