package com.jacsonferreira.apimc.domain.enums;

public enum ClientType {
	INDIVIDUALPERSON(1,"INDIVIDUALPERSON"),
	JURIDICALPERSON(2,"JURIDICALPERSON");

	private Integer cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod =cod;
		this.description =description;
	}
	public Integer getCod() {
		return cod;
	}
	public String getDescription() {
		return description;
	}
	
	public static  ClientType toEnum(Integer cod) {
		if (cod == null) {
			return null; 	
		}
		for(ClientType xClientType : ClientType.values()) {
			if (cod.equals(xClientType.getCod())) {
				return xClientType;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
