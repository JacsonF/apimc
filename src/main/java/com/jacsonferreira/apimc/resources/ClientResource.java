package com.jacsonferreira.apimc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.dto.ClientDTO;
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

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid  @RequestBody ClientDTO client) {
		Client obj = service.fromDTO(client);
		obj.setId(id);
		obj= service.update(obj);	 
		
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		
	service.delete(id);
	return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>>findall(){
		List<Client> categories = service.findAll();
		
		List<ClientDTO> categoryDTOs = categories.stream().map( obj -> new ClientDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(categoryDTOs);
	}
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>>findPage(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction){
		
		Page<Client> clients= service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClientDTO> clientDTOs = clients.map( obj -> new ClientDTO(obj)); 
		return ResponseEntity.ok().body(clientDTOs);
	}
	
}
