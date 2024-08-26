package com.xpert.TravellingAgency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutPageController {
	
	@GetMapping
	public String getAboutPage(Model model) {
		
		model.addAttribute("navigationPage", "about");
		return "about";
	}

}
