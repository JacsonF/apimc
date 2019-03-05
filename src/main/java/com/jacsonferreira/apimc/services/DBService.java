package com.jacsonferreira.apimc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacsonferreira.apimc.domain.Address;
import com.jacsonferreira.apimc.domain.BoletoPayment;
import com.jacsonferreira.apimc.domain.CardPayment;
import com.jacsonferreira.apimc.domain.Category;
import com.jacsonferreira.apimc.domain.City;
import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.domain.ItemOrder;
import com.jacsonferreira.apimc.domain.Order;
import com.jacsonferreira.apimc.domain.Payment;
import com.jacsonferreira.apimc.domain.Product;
import com.jacsonferreira.apimc.domain.State;
import com.jacsonferreira.apimc.domain.enums.ClientType;
import com.jacsonferreira.apimc.domain.enums.PaymentState;
import com.jacsonferreira.apimc.repositories.AddressRepository;
import com.jacsonferreira.apimc.repositories.CategoryRepository;
import com.jacsonferreira.apimc.repositories.CityRepositoriy;
import com.jacsonferreira.apimc.repositories.ClientRepository;
import com.jacsonferreira.apimc.repositories.ItemOrderRepository;
import com.jacsonferreira.apimc.repositories.OrderRepository;
import com.jacsonferreira.apimc.repositories.PaymentRepository;
import com.jacsonferreira.apimc.repositories.ProductRepository;
import com.jacsonferreira.apimc.repositories.StateRepository;

@Service
public class DBService {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	CityRepositoriy cityRepositoriy;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ItemOrderRepository itemOrderRepository;

	public void instantiateTestDataBase() throws ParseException {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama, mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritorio", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City city = new City(null, "Belo Horizonte", st1);
		City city2 = new City(null, "Santos", st2);
		City city3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(city));
		st2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.save(Arrays.asList(st1, st2));
		cityRepositoriy.save(Arrays.asList(city, city2, city3));

		Client cli1 = new Client(null, "Maria Silva", "jacsonferreira1994@gmail.com", "36378912377", ClientType.INDIVIDUALPERSON);
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, city);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, city2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		clientRepository.save(cli1);
		addressRepository.save(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pgto1 = new CardPayment(null, PaymentState.PAY, ped1, 6);
		ped1.setPayment(pgto1);

		Payment pgto2 = new BoletoPayment(null, PaymentState.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pgto2);

		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderRepository.save(Arrays.asList(ped1, ped2));
		paymentRepository.save(Arrays.asList(pgto1, pgto2));

		ItemOrder ip1 = new ItemOrder(ped1, p1, 0.00, 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(ped1, p3, 0.00, 2, 80.00);
		ItemOrder ip3 = new ItemOrder(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemOrderRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
