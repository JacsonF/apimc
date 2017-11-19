package com.jacsonferreira.apimc.domain;
import java.util.Date;
import javax.persistence.Entity;

import com.jacsonferreira.apimc.domain.enums.PaymentState;
@Entity
public class BoletoPayment  extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Date datePayment;
	private Date dateExpiry;
	
	
	public BoletoPayment() {
		
	}


	public BoletoPayment(Integer id, PaymentState state, Order order, Date datepayment, Date dateExpiry) {
		super(id, state, order);
		this.dateExpiry = dateExpiry;
		this.datePayment = datepayment;
	}


	public Date getDatePayment() {
		return datePayment;
	}


	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}


	public Date getDateExpiry() {
		return dateExpiry;
	}


	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}
}
