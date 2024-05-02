package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateDataController {

    @GetMapping("/private-data")
    @Secured("ROLE_ADMIN")
    public String privateData() {
        return "private_data";
    }
}
