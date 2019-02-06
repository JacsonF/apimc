package com.jacsonferreira.apimc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.transaction.Transactional;

import com.jacsonferreira.apimc.domain.BoletoPayment;
import com.jacsonferreira.apimc.domain.ItemOrder;
import com.jacsonferreira.apimc.domain.Order;
import com.jacsonferreira.apimc.domain.enums.PaymentState;
import com.jacsonferreira.apimc.repositories.ItemOrderRepository;
import com.jacsonferreira.apimc.repositories.OrderRepository;
import com.jacsonferreira.apimc.repositories.PaymentRepository;
import com.jacsonferreira.apimc.repositories.ProductRepository;
import com.jacsonferreira.apimc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ClientService clientService;

	public Order Find(Integer id) {
		Order obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("objeto não encontrado id: " + id + " , tipo: " + Order.class.getName());
		}
		return obj;
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.Find(obj.getClient().getId()));
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof BoletoPayment) {
			BoletoPayment boletoPayment = (BoletoPayment) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(boletoPayment, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (ItemOrder order : obj.getItens()) {
			order.setDiscount(0.0);
			order.setProduct(productService.Find(order.getProduct().getId())); 
			order.setPrice(order.getProduct().getPreco());
			order.setOrder(obj);
		}
		itemOrderRepository.save(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
