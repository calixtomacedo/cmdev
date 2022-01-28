package br.com.cmdev.login.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ResponseEntity<?> hello() {
		
		var response = "Hello: Meu teste funfou!";
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
