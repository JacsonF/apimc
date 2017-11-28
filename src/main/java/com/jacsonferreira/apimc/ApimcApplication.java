package com.jacsonferreira.apimc;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


@SpringBootApplication
public class ApimcApplication implements CommandLineRunner {
	
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
	
	public static void main(String[] args) {
		SpringApplication.run(ApimcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Category cat1 = new Category(null,"Informática");
		Category cat2 = new Category(null,"Escritório");
		Category cat3 = new Category(null, "Cama, mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product p1 = new Product(null,"Computador", 2000.00);
		Product p2 = new Product(null,"Impressora", 800.00);
		Product p3 = new Product(null,"Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRepository.save(Arrays.asList(p1,p2,p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City city = new City(null, "Belo Horizonte", st1);
		City city2 = new City(null, "Santos", st2);
		City city3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(city));
		st2.getCities().addAll(Arrays.asList(city2,city3));
		
		stateRepository.save(Arrays.asList(st1,st2));
		cityRepositoriy.save(Arrays.asList(city,city2,city3));
		
		
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.INDIVIDUALPERSON);
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, city);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, city2);
		
		cli1.getAddresses().addAll(Arrays.asList(e1,e2));
	
		clientRepository.save(cli1);
		addressRepository.save(Arrays.asList(e1,e2));
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");
	
		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pgto1 = new CardPayment(null, PaymentState.PAY, ped1, 6);
		ped1.setPayment(pgto1);
		
		Payment pgto2 = new BoletoPayment(null, PaymentState.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pgto2);
		
		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));
		
		orderRepository.save(Arrays.asList(ped1,ped2));
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
