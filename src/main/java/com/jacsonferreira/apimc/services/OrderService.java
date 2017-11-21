package com.jacsonferreira.apimc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Order;
import com.jacsonferreira.apimc.repositories.OrderRepository;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	public Order Find(Integer id) {
		Order obj = repo.findOne(id);
		if (obj ==null) {
			throw new ObjectNotFoundException("objeto não encontrado id: " +id +" , tipo: " +Order.class.getName());
		}
		return obj;
	}
}
