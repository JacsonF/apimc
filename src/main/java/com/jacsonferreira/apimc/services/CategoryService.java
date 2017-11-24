package com.jacsonferreira.apimc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.repositories.CategoryRepository;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category Find(Integer id) {
		Category obj = repo.findOne(id);
		if (obj ==null) {
			throw new ObjectNotFoundException("objeto não encontrado id: " +id +" , tipo: " +Category.class.getName());
		}
		return obj;
	}
	public Category insert(Category category) {
		category.setId(null);
		return repo.save(category);
	}
}
