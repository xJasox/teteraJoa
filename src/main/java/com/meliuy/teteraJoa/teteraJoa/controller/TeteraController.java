// src/main/java/com/meliuy/teteraJoa/teteraJoa/controller/TeteraController.java
package com.meliuy.teteraJoa.teteraJoa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@Tag(name = "Tetera", description = "Operations related to Tetera systems")
public class TeteraController {

    private static final Map<String, String> SYSTEM_CODES = new HashMap<>() {{
        put("navigation", "NAV-01");
        put("communications", "COM-02");
        put("life_support", "LIFE-03");
        put("engines", "ENG-04");
        put("deflector_shield", "SHLD-05");
    }};
    private String damagedSystem = "navigation";

    @Operation(summary = "Get repair bay", description = "Retrieve the current system code and available systems.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/repair-bay")
    public String getRepairBay(Model model) {
        model.addAttribute("code", SYSTEM_CODES.get(damagedSystem));
        model.addAttribute("systems", SYSTEM_CODES.keySet());
        return "html";
    }

    @Operation(summary = "Change system", description = "Change the damaged system to a new one.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Found")
    })
    @PostMapping("/change-system")
    public String changeSystem(@RequestParam String system) {
        if (SYSTEM_CODES.containsKey(system)) {
            damagedSystem = system;
        }
        return "redirect:/repair-bay";
    }

    @Operation(summary = "Reset system", description = "Reset the system code to 'RESET'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/reset")
    public String resetSystem(Model model) {
        model.addAttribute("code", "RESET");
        return "html";
    }
}