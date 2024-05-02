package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.AdminService;
import com.example.service.HomeService;
import com.example.service.UserService;

@Controller
public class HomeController {

	private final HomeService homeService;
	private final UserService userService;
	private final AdminService adminService;
	
	public HomeController(HomeService homeService, UserService userService, AdminService adminService) {
		this.homeService = homeService;
		this.userService = userService;
		this.adminService = adminService;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("text", homeService.getText());
		return "index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "redirect:/";
	}
	
	@GetMapping("/user")
	public String user(Model model) {
		model.addAttribute("text", userService.getText());
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("text", adminService.getText());
		return "admin";
	}
}
