package com.jacsonferreira.apimc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jacsonferreira.apimc.domain.Client;
import com.jacsonferreira.apimc.dto.ClientDTO;
import com.jacsonferreira.apimc.repositories.ClientRepository;
import com.jacsonferreira.apimc.resources.exception.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private HttpServletRequest httpResquest;

	@Override
	public void initialize(ClientUpdate ann) {
	}
	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Map<String,String> map	= (Map<String, String>) httpResquest
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer uriId =Integer.parseInt(map.get("id")) ;
		Client client = clientRepository.findByEmail(objDto.getEmail());
		if (client != null && !client.getId().equals(uriId)) {
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