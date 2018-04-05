package com.thinking_cursor.springbootiful.ldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class TryNewThingsController {

	@GetMapping("/good")
	public String good() {
		return "Good...Bad...Ugly";
	}
	
	@GetMapping("/error")
	public String bad() {
		return "something horrible";
	}
}
