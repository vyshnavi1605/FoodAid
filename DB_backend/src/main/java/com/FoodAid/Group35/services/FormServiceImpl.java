package com.FoodAid.Group35.services;

import com.FoodAid.Group35.entity.Form;
import com.FoodAid.Group35.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {
	@Autowired
	private FormRepository formRepository;

//	List<Form> list;

	public FormServiceImpl() {
//	list = new ArrayList<>();
//list.add(new Form(555, "James", " how to support the organisation"));

	}

	public List<Form> getForm() {
		// TODO Auto-generated method stub
		return formRepository.findAll();
	}

	

	




	@Override
	public Form addForm(Form form) {
//		list.add(form);
		formRepository.save(form);
		return form;
	}

	@Override
	public Form getForm(long formId) {
//		Form c = null;
//		for (Form form : list) {
//			if (form.getId() == formId) {
//				c = form;
//				break;

			//}
		//}
		return formRepository.getOne(formId);
	}
	@Override
	public Form getForm(Long formId) {
		// TODO Auto-generated method stub
		return null;
	}

	


	




}
