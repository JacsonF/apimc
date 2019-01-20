package com.jacsonferreira.apimc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.jacsonferreira.apimc.domain.Product;
import com.jacsonferreira.apimc.dto.ProductDTO;
import com.jacsonferreira.apimc.resources.utils.URL;
import com.jacsonferreira.apimc.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = service.Find(id);
		return ResponseEntity.ok(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		String nameDecoded = URL.decoderParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> products = service.search(nameDecoded, ids,page, linesPerPage, orderBy, direction);

		Page<ProductDTO> ProductDTO = products.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(ProductDTO);
	}

}
