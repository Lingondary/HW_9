package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicDataController {

    @GetMapping("/public-data")
    public String publicData(Model model) {
        model.addAttribute("text", "Public Data");
        return "public_data";
    }
}
