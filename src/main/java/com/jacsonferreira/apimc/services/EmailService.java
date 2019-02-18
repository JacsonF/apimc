package com.jacsonferreira.apimc.services;

import org.springframework.mail.SimpleMailMessage;

import com.jacsonferreira.apimc.domain.Order;

public interface EmailService {

	void senderOrderConfirmationEmail(Order order);

	void sendEmail(SimpleMailMessage msg);
}
