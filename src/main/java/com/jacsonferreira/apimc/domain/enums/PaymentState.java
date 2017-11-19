package com.jacsonferreira.apimc.domain.enums;

public enum PaymentState {
	PENDING(1,"pending"),
	PAY(2,"pay"),
	CANCELLED(3,"cancelled");	
	private int cod; 
	private String description;
	
	private PaymentState(int cod, String description) {
		this.cod =cod;
		this.description =description;
	}
	public int getCod() {
		return cod;
	}
	public String getDescription() {
		return description;
	}
	
	public static  PaymentState toEnum(Integer cod) {
		if (cod == null) {
			return null; 	
		}
		for(PaymentState xPaymentState : PaymentState.values()) {
			if (cod.equals(xPaymentState.getCod())) {
				return xPaymentState;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
