package com.alinicanedo.ecommerce.repositories;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
