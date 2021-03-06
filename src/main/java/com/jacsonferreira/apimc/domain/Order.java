package com.jacsonferreira.apimc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TB_ORDER")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "yyy/MM/dd hh:mm")
	private Date instant;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "client_id")

	private Client client;
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@OneToMany(mappedBy = "id.order")
	private Set<ItemOrder> itens = new HashSet<>();

	public Order() {

	}

	public Order(Integer id, Date instant, Client client, Address address) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.address = address;
	}

	public double getTotalValue() {
		double soma = 0.0;
		for (ItemOrder itemOrder : itens) {
			soma = soma + itemOrder.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ItemOrder> getItens() {
		return itens;
	}

	public void setItens(Set<ItemOrder> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("Order number: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Client: ");
		builder.append(getClient().getName());
		builder.append(", Payment status: ");
		builder.append(getPayment().getState().getDescription());
		builder.append("\nDetails:\n");
		for (ItemOrder item : getItens()) {
			builder.append(item.toString());
		}
		builder.append("Total Value: ");
		builder.append(nf.format(getTotalValue()));
		return builder.toString();
	}

}
