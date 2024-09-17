package com.FoodAid.Group35.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodAid.Group35.entity.Client;
import com.FoodAid.Group35.repository.ClientRepository;

import java.util.List;

@Service
@Transactional
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

//	public ClientService(ClientRepository clientRepository) {
//		this.clientRepository=clientRepository;
//	}
//	public void saveClient(Client client) {
//		clientRepository.save(client);
//	}

//	public List<?> getCompanies(String companyName){
//
//	}
	
}


