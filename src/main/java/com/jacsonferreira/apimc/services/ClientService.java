package com.jacsonferreira.apimc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Address;
import com.jacsonferreira.apimc.domain.City;
import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.domain.enums.ClientType;
import com.jacsonferreira.apimc.dto.ClientDTO;
import com.jacsonferreira.apimc.dto.ClientNewDTO;
import com.jacsonferreira.apimc.repositories.AddressRepository;
import com.jacsonferreira.apimc.repositories.CityRepositoriy;
import com.jacsonferreira.apimc.repositories.ClientRepository;
import com.jacsonferreira.apimc.services.exceptions.DataIntegrityExeption;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	@Autowired
	private CityRepositoriy cityRepository;
	@Autowired
	private AddressRepository addressRepository;

	public Client insert(Client client) {
		client.setId(null);
		client = repo.save(client);
		addressRepository.save(client.getAddresses());
		return client;
	}

	public Client Find(Integer id) {
		Client obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Cannot find object id: " + id + " , type: " + Client.class.getName());
		}
		return obj;
	}

	public Client update(Client client) {
		Client newObj = Find(client.getId());
		updateData(newObj, client);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		Find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExeption("Can not delete a client that has orders");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}

	public Client fromDTO(ClientNewDTO clientDTO) {
		Client client = new Client(null, clientDTO.getName(), clientDTO.getEmail(), clientDTO.getCpfOrCnpj(),
				ClientType.toEnum(clientDTO.getClientType()));
		
		City city = cityRepository.findOne(clientDTO.getCityId());
		
		Address address = new Address(null, clientDTO.getPatio(), clientDTO.getNumber(), clientDTO.getComplement(),
				clientDTO.getDistrict(), clientDTO.getCep(), client, city);

		client.getAddresses().add(address);
		client.getPhones().add(clientDTO.getPhone1());
		if (clientDTO.getPhone2() != null) {
			client.getPhones().add(clientDTO.getPhone2());
		}
		if (clientDTO.getPhone3() != null) {
			client.getPhones().add(clientDTO.getPhone3());
		}
		return client;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
