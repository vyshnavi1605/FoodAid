package com.FoodAid.Group35.services;
import java.util.List;

import com.FoodAid.Group35.entity.Form;
public interface FormService {
	public Form getForm(long formId);

	public List <Form> getForm();
	public Form getForm(Long formId);
	public Form addForm(Form form);

	

}

