package com.countrymanager.countrymanager;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/")
	public String index() throws IOException, ParseException {
		return new CntryManager().getCountries().toString();
	}

}