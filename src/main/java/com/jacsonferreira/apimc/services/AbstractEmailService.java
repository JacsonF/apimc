package com.jacsonferreira.apimc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.jacsonferreira.apimc.domain.Order;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;
	@Override
	public void senderOrderConfirmationEmail(Order order) {
		SimpleMailMessage msg  = prepareSimpleMailMenssageOrder(order);
		sendEmail(msg);
		
		
	}

	protected SimpleMailMessage prepareSimpleMailMenssageOrder(Order order) {
		SimpleMailMessage msg  =new SimpleMailMessage();
		msg.setTo(order.getClient().getEmail());
		msg.setFrom(sender);
		msg.setSubject("Order confirmed"+ order.getId());
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText(order.toString());
		return msg;
	}
}
