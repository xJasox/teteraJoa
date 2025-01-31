package com.meliuy.teteraJoa.teteraJoa.service;

import com.meliuy.teteraJoa.teteraJoa.exception.InvalidSystemException;
import com.meliuy.teteraJoa.teteraJoa.model.System;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SystemService {

    private static final Map<String, String> SYSTEM_CODES = new HashMap<>() {{
        put("navigation", "NAV-01");
        put("communications", "COM-02");
        put("life_support", "LIFE-03");
        put("engines", "ENG-04");
        put("deflector_shield", "SHLD-05");
    }};
    private String damagedSystem = "navigation";

    public System getStatus() {
        return new System(damagedSystem);
    }

    public void changeSystem(String system) {
        if (SYSTEM_CODES.containsKey(system)) {
            this.damagedSystem = system;
        } else {
            throw new InvalidSystemException("Invalid system: " + system);
        }
    }
}