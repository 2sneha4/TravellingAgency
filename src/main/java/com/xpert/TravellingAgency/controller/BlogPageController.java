package com.xpert.TravellingAgency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogPageController {
	
	@GetMapping
	public String getBlogPage(Model model) {
		
		model.addAttribute("navigationPage", "blog");
		return "blog";
	}
	
	@GetMapping("/blog-single")
	public String getBlogSinglePage(Model model) {
	
		model.addAttribute("navigationPage", "blog");
		return "blog-single";
	}

}
