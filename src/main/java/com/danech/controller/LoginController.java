package com.danech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(
			Model model,
			@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout
			) {
		if(logout!=null)
			model.addAttribute("logout", logout);
		if(error!=null)
			model.addAttribute("error", error);
		return "login";
	}
	
}