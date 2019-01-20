package com.jacsonferreira.apimc.dto;

import java.io.Serializable;

import com.jacsonferreira.apimc.domain.Product;


public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Double preco;

	
	public ProductDTO() {
	}

	public ProductDTO(Product obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}

	/**
	 * @return the preco
	 */
	public Double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
