package br.com.cmdev.cmdevsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String home(Model model) {

		model.addAttribute("home", "CMDEV - Desenvolvimentos.");

		return "home";
	}
	
	/*
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError(){
		return "redirect:/home";
	}
	*/

}
