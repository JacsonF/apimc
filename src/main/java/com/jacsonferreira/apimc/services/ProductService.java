package com.jacsonferreira.apimc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.domain.Product;
import com.jacsonferreira.apimc.repositories.CategoryRepository;
import com.jacsonferreira.apimc.repositories.ProductRepository;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	@Autowired
	private CategoryRepository categoryRepository;

	public Product Find(Integer id) {
		Product obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Cannot find object id: " + id + " , type: " + Product.class.getName());
		}
		return obj;
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAll(ids);

		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
