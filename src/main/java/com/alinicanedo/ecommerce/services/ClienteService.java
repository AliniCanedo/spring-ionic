package com.alinicanedo.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alinicanedo.ecommerce.domain.Cliente;
import com.alinicanedo.ecommerce.repositories.ClienteRepository;
import com.alinicanedo.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
