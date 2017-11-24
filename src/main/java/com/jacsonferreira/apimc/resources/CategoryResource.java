package com.jacsonferreira.apimc.resources;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.dto.CategoryDTO;
import com.jacsonferreira.apimc.services.CategoryService;
import com.jacsonferreira.apimc.services.exceptions.DataIntegrityExeption;



@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
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
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Category category) {
		category.setId(id);
		category = service.update(category);	 
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>>findall(){
		List<Category> categories = service.findAll();
		
		List<CategoryDTO> categoryDTOs = categories.stream().map( obj -> new CategoryDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(categoryDTOs);
	}
}
