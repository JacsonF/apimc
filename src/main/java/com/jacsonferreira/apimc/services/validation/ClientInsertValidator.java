package com.jacsonferreira.apimc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.domain.enums.ClientType;
import com.jacsonferreira.apimc.dto.ClientNewDTO;
import com.jacsonferreira.apimc.repositories.ClientRepository;
import com.jacsonferreira.apimc.resources.exception.FieldMessage;
import com.jacsonferreira.apimc.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getClientType().equals(ClientType.INDIVIDUALPERSON.getCod())
				&& !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF invalid!"));
		}
		if (objDto.getClientType().equals(ClientType.JURIDICALPERSON.getCod())
				&& !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ invalid"));
		}

		Client client = clientRepository.findByEmail(objDto.getEmail());
		if (client != null) {
			list.add(new FieldMessage("email", "Email already exists"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}