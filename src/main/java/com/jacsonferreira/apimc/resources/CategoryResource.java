package com.jacsonferreira.apimc.resources;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = service.Find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@RequestBody Category category){
	 category =  service.insert(category);
	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			 .path("/{id}").buildAndExpand(category.getId()).toUri();
	
	return ResponseEntity.created(uri).build();
	}
}
