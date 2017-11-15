package com.jacsonferreira.apimc.resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResources {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = service.Find(id);
		System.out.println("===========>"+obj.getProducts());
		return ResponseEntity.ok(obj);
	}
}
