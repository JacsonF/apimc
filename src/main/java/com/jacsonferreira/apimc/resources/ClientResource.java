package com.jacsonferreira.apimc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client obj = service.Find(id);	
		return ResponseEntity.ok(obj);
	}
}
