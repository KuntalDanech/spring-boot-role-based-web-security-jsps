package com.danech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("agent")
public class AgentController {
	
	@GetMapping("index")
	public String index() {
		return "agent/index";
	}
}
