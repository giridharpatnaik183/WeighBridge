package com.ld.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/get")
	public String getPage() {
		return "login";
		
	}
}
