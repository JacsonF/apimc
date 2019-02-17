package com.jacsonferreira.apimc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.dto.CategoryDTO;
import com.jacsonferreira.apimc.repositories.CategoryRepository;
import com.jacsonferreira.apimc.services.exceptions.DataIntegrityExeption;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category Find(Integer id) {
		Category obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Cannot find object id: " + id + " , type: " + Category.class.getName());
		}
		return obj;
	}

	public Category insert(Category category) {
		category.setId(null);
		return repo.save(category);
	}

	public Category update(Category category) {
		Category newObj = Find(category.getId());
		updateData(newObj, category);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		Find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExeption("Can not delete a category that has products");
		}
	}

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
