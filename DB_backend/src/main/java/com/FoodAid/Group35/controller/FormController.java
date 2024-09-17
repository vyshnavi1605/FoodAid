package com.FoodAid.Group35.controller;



import com.FoodAid.Group35.services.FormService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.FoodAid.Group35.entity.Form;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")

public class FormController {
	@Autowired
	private FormService formService;
	@GetMapping("/form")
	public List<Form> getForm(){
		return (List<Form>) this.formService.getForm();

	}
	@GetMapping ("/form/ {formId}")
	public Form getForm(@PathVariable String formId){
		return this.formService.getForm(Long.parseLong(formId));

	}
	@PostMapping ("/form")
	public Form addForm(@RequestBody Form form) {
		return this.formService.addForm(form);

	}


}




