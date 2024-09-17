package com.FoodAid.Group35.controller;

import com.FoodAid.Group35.entity.Client;
import com.FoodAid.Group35.repository.ClientRepository;
//import com.FoodAid.Group35.services.ClientService;
import com.FoodAid.Group35.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClientController {

	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping("/auth/company/{companyName}")
	public List<String> workForCompany(@PathVariable String companyName){
		return companyRepository.fetchByWorkingCompany(companyName);
	}

	/*
	@RequestMapping("/auth/Hello")
	public String index() {
		return "hello, this works.";
	}
	*/
	

	

	
	
}
