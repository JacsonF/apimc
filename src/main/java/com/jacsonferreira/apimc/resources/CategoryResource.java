package com.jacsonferreira.apimc.resources;
import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.dto.CategoryDTO;
import com.jacsonferreira.apimc.services.CategoryService;



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
	public ResponseEntity<Void>insert(@Valid @RequestBody CategoryDTO objDTO){
	Category obj = service.fromDTO(objDTO);
		obj =  service.insert(obj);
	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			 .path("/{id}").buildAndExpand(obj.getId()).toUri();
	
	return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid  @RequestBody CategoryDTO category) {
		Category obj = service.fromDTO(category);
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
	public ResponseEntity<List<CategoryDTO>>findall(){
		List<Category> categories = service.findAll();
		
		List<CategoryDTO> categoryDTOs = categories.stream().map( obj -> new CategoryDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(categoryDTOs);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>>findPage(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction){
		
		Page<Category> categories = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoryDTO> categoryDTOs = categories.map( obj -> new CategoryDTO(obj)); 
		return ResponseEntity.ok().body(categoryDTOs);
	}
}
