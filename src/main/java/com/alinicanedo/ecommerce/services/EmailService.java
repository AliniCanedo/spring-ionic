package com.alinicanedo.ecommerce.services;

import org.springframework.mail.SimpleMailMessage;

import com.alinicanedo.ecommerce.domain.Cliente;
import com.alinicanedo.ecommerce.domain.Pedido;

public interface EmailService {

void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
