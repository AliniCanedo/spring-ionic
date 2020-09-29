package com.alinicanedo.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.alinicanedo.ecommerce.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
