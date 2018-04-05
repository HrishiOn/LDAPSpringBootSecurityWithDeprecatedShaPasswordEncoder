package com.thinking_cursor.springbootiful.ldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TryNewThingsController {

	@GetMapping("/lkj")
	public String good() {
		return "Good...Bad...Ugly";
	}
}
