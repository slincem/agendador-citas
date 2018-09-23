package co.com.meeting.meetingsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaKelly {
	
	
	@GetMapping("/cita")
	public String exponerServicio() {
		
		return "Hola Mundo";
	}

}
