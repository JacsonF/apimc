package com.jacsonferreira.apimc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.repositories.ClientRepository;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	public Client Find(Integer id) {
		Client obj = repo.findOne(id);
		if (obj ==null) {
			throw new ObjectNotFoundException("objeto não encontrado id: " +id +" , tipo: " +Client.class.getName());
		}
		return obj;
	}
}
