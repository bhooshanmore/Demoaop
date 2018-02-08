package com.stackroute.activitystream.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String homepage(){
		return "Checking Aspect !!";
	}
	
}
