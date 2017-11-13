package com.jacsonferreira.apimc;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.domain.Product;
import com.jacsonferreira.apimc.repositories.CategoryRepository;
import com.jacsonferreira.apimc.repositories.ProductRepository;


@SpringBootApplication
public class ApimcApplication implements CommandLineRunner {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ApimcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Category cat1 = new Category(null,"Informática");
		Category cat2 = new Category(null,"Escritório");
		
		Product p1 = new Product(null,"Computador", 2000.00);
		Product p2 = new Product(null,"Impressora", 800.00);
		Product p3 = new Product(null,"Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		categoryRepository.save(Arrays.asList(cat1,cat2));
		productRepository.save(Arrays.asList(p1,p2,p3));
	}
}
