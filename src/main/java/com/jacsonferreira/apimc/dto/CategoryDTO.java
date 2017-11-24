package com.jacsonferreira.apimc.dto;

import java.io.Serializable;

import com.jacsonferreira.apimc.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	
	public CategoryDTO() {
		
	}
	public CategoryDTO(Category category) {
		id= category.getId();
		nome = category.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
