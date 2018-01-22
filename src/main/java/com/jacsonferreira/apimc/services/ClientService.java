package com.jacsonferreira.apimc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.dto.ClientDTO;
import com.jacsonferreira.apimc.repositories.ClientRepository;
import com.jacsonferreira.apimc.services.exceptions.DataIntegrityExeption;
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
	public Client update(Client client) {
		 Client newObj= Find(client.getId());
		 updateData(newObj, client);
		return repo.save(newObj);
	}
	public void delete(Integer id) {
		Find(id);
		try {
		repo.delete(id);	
		}catch (DataIntegrityViolationException  e) {
			throw new DataIntegrityExeption("Can not delete a client that has orders");
		}
	}
	public List<Client> findAll() {
		return repo.findAll();
	}
	public Page<Client> findPage(Integer page , Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(),clientDTO.getEmail(),null,null);
	}	
	private void updateData(Client newObj, Client obj) {
		newObj.setName( obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
