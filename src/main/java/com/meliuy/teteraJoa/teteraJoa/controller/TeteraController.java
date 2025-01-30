package com.meliuy.teteraJoa.teteraJoa.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TeteraController {

    private static final Map<String, String> SYSTEM_CODES = new HashMap<>() {{
        put("navigation", "NAV-01");
        put("communications", "COM-02");
        put("life_support", "LIFE-03");
        put("engines", "ENG-04");
        put("deflector_shield", "SHLD-05");
    }};
    private String damagedSystem = "navigation";

    @GetMapping("/repair-bay")
    public String getRepairBay(Model model) {
        model.addAttribute("code", SYSTEM_CODES.get(damagedSystem));
        model.addAttribute("systems", SYSTEM_CODES.keySet());
        return "html";
    }

    @PostMapping("/change-system")
    public String changeSystem(@RequestParam String system) {
        if (SYSTEM_CODES.containsKey(system)) {
            damagedSystem = system;
        }
        return "redirect:/repair-bay";
    }

    @GetMapping("/reset")
    public String resetSystem(Model model) {
        model.addAttribute("code", "RESET");
        return "html";
    }
}