package com.jacsonferreira.apimc.resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jacsonferreira.apimc.domain.Order;
import com.jacsonferreira.apimc.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResources {

	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Order obj = service.Find(id);
		return ResponseEntity.ok(obj);
	}
}
