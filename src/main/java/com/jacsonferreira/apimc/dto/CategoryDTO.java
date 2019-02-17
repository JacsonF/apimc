package com.jacsonferreira.apimc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jacsonferreira.apimc.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="name is required")
	@Length(min=5,max=80, message="the size should be between 5 and 80 characters")
	private String name;
	
	public CategoryDTO() {
		
	}
	public CategoryDTO(Category category) {
		id= category.getId();
		name = category.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}
	
	
}
